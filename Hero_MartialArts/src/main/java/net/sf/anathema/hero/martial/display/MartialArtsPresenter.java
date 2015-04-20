package net.sf.anathema.hero.martial.display;

import net.sf.anathema.hero.martial.model.MartialArtsModel;
import net.sf.anathema.hero.martial.model.StyleName;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.library.view.OptionalPropertyEntryView;
import net.sf.anathema.library.view.property.OptionalPropertiesView;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.List;

public class MartialArtsPresenter {
  private final MartialArtsModel model;
  private final Resources resources;
  private final OptionalPropertiesView view;

  public MartialArtsPresenter(MartialArtsModel model, Resources resources, OptionalPropertiesView view) {
    this.model = model;
    this.resources = resources;
    this.view = view;
  }

  public void initPresentation() {
    List<StyleName> allStyles = model.getAllStyles();
    OptionalPropertyEntryView selectionView = view.addSelectionView();
    ObjectSelectionView<StyleName> selection = selectionView.addSelection(new MartialArtsStyleConfiguration(resources));
    selection.setObjects(allStyles);
    selection.addObjectSelectionChangedListener(model::selectStyle);
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setTooltip(resources.getString("MartialArts.Add.Tooltip"));
    tool.setCommand(model::learnSelectedStyle);
    model.whenStyleIsSelected(() -> updateSelectionInView(selection));
    //model.whenStyleIsLearned(() -> updateLearnedStylesInView());
    updateSelectionInView(selection);
  }

  private void updateLearnedStylesInView() {
    //view.addItemView()
  }

  private void updateSelectionInView(ObjectSelectionView<StyleName> selection) {
    selection.setSelectedObject(model.getSelectedStyle());
  }
}
