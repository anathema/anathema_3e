package net.sf.anathema.hero.martial.display;

import net.sf.anathema.hero.martial.model.MartialArtsModel;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.library.view.OptionalPropertyEntryView;
import net.sf.anathema.library.view.trait.OptionalTraitItemView;
import net.sf.anathema.library.view.trait.OptionalTraitsView;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.HashMap;
import java.util.Map;

public class MartialArtsPresenter {
  private final MartialArtsModel model;
  private final Resources resources;
  private final OptionalTraitsView view;
  private final Map<Trait, OptionalTraitItemView> viewsByEntry = new HashMap<>();

  public MartialArtsPresenter(MartialArtsModel model, Resources resources, OptionalTraitsView view) {
    this.model = model;
    this.resources = resources;
    this.view = view;
  }

  public void initPresentation() {
    OptionalPropertyEntryView selectionView = view.addSelectionView();
    ObjectSelectionView<Trait> selection = selectionView.addSelection(new MartialArtsStyleConfiguration(resources));
    updateAvailableStyles(selection);
    selection.addObjectSelectionChangedListener(model::selectStyle);
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setTooltip(resources.getString("MartialArts.Add.Tooltip"));
    tool.setCommand(model::learnSelectedStyle);
    model.whenStyleIsSelected(() -> updateSelectionInView(selection));
    model.whenStyleIsLearned((style) -> {
      addSubView(style);
      updateAvailableStyles(selection);
    });
    model.whenStyleIsForgotten((style) -> {
      removeSubView(style);
      updateAvailableStyles(selection);
    });
    model.whenCharacterBecomesAMartialArtist(tool::enable);
    model.whenCharacterNoLongerIsAMartialArtist(tool::disable);
    updateSelectionInView(selection);
    initSubViews();
  }

  private void initSubViews() {
    for (Trait style : model.getLearnedStyles()) {
      addSubView(style);
    }
  }

  private void updateAvailableStyles(ObjectSelectionView<Trait> selection) {
    selection.setObjects(model.getAvailableStyles());
  }

  private void addSubView(Trait style) {
    String styleLabel = resources.getString(style.getType().getId());
    OptionalTraitItemView subView = view.addItemView(styleLabel, style.getMaximalValue(), new BasicUi().getRemoveIconPath());
    new TraitPresenter(style, subView.getIntValueView()).initPresentation();
    viewsByEntry.put(style, subView);
    subView.addButtonListener(() -> model.forget(style));
  }

  private void removeSubView(Trait style) {
    OptionalTraitItemView viewToRemove = viewsByEntry.remove(style);
    viewToRemove.remove();
  }

  private void updateSelectionInView(ObjectSelectionView<Trait> selection) {
    selection.setSelectedObject(model.getSelectedStyle());
  }
}
