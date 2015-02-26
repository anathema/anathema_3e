package net.sf.anathema.library.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.model.KnownOptionalTrait;
import net.sf.anathema.library.model.OptionalTraitCategory;
import net.sf.anathema.library.model.OptionalTraitOption;
import net.sf.anathema.library.model.OptionalTraitsModel;
import net.sf.anathema.library.model.RemovableEntryListener;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.library.view.OptionalTraitEntryView;
import net.sf.anathema.library.view.OptionalTraitItemView;
import net.sf.anathema.library.view.OptionalTraitsView;
import net.sf.anathema.platform.taskbar.BasicUi;

public class CategorizedOptionalTraitPresenter<
	C extends OptionalTraitCategory,
	O extends OptionalTraitOption,
	T extends KnownOptionalTrait<O>> {

  private ObjectSelectionView<O> traitBox;
  private ITextView textBox;
  private final OptionalTraitsView view;
  private final Resources resources;
  private final OptionalTraitsModel<C, O, T> model;
  private final Map<T, OptionalTraitItemView> viewsByEntry = new HashMap<>();
  private final int traitMaxValue;

  public CategorizedOptionalTraitPresenter(OptionalTraitsModel<C, O, T> model, OptionalTraitsView view, Resources resources, int traitMaxValue) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.traitMaxValue = traitMaxValue;
  }

  public void initPresentation() {
    OptionalTraitEntryView selectionView = view.addSelectionView();
    Tool tool = initCreationView(selectionView);
    initModelListening(selectionView, tool);
    for (T trait : model.getKnownTraits()) {
      addSubView(trait);
    }
    reset();
    List<Trait> contingentTraits = model.getContingentTraits();
    for (Trait trait : contingentTraits) {
      trait.addCurrentValueListener(value -> refreshTraitList());
    }
  }

  private void addSubView(T trait) {
    OptionalTraitItemView subView = view.addKnownTrait(trait.toString(), traitMaxValue, new BasicUi().getRemoveIconPath());
    new TraitPresenter(trait, subView.getIntValueView()).initPresentation();
    viewsByEntry.put(trait, subView);
    subView.addButtonListener(() -> model.removeEntry(trait));
  }

  private void initModelListening(OptionalTraitEntryView selectionView, Tool tool) {
    model.addModelChangeListener(new RemovableEntryListener<T>() {
      @Override
      public void entryAdded(T trait) {
        addSubView(trait);
        reset();
      }

      @Override
      public void entryRemoved(T trait) {
        OptionalTraitItemView itemView = viewsByEntry.get(trait);
        itemView.remove();
      }

      @Override
      public void entryAllowed(boolean complete) {
        if (complete) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    });
  }

  private Tool initCreationView(OptionalTraitEntryView selectionView) {
  	if (model.hasCategories()) {
  		addCategorySelection(selectionView);
  	}
    this.traitBox = addTraitSelection(selectionView);
    addDescriptionBox(selectionView);
    return addCommitTool(selectionView);
  }

  private void addCategorySelection(OptionalTraitEntryView selectionView) {
    ObjectSelectionView<C> typeView = selectionView.addSelection(new ToStringConfiguration<>());
    typeView.setObjects(model.getAvailableCategories());
    typeView.setSelectedObject(model.getCurrentCategory());
    typeView.addObjectSelectionChangedListener(model::setCurrentCategory);
    model.whenCategoryChanges(() -> {
      typeView.setSelectedObject(model.getCurrentCategory());
      refreshTraitList();
    });
  }

  private ObjectSelectionView<O> addTraitSelection(OptionalTraitEntryView selectionView) {
    ObjectSelectionView<O> traitView = selectionView.addSelection(new ToStringConfiguration<>());
    traitView.setObjects(model.getCurrentTraitOptions());
    traitView.setSelectedObject(model.getSelectedTraitOption());
    traitView.addObjectSelectionChangedListener(model::setSelectedTraitOption);
    model.whenSelectedOptionChanges(() -> {
      traitView.setSelectedObject(model.getSelectedTraitOption());
      textBox.suggestCompletions(model.getSuggestedDescriptions());
    });
    return traitView;
  }

  private void addDescriptionBox(OptionalTraitEntryView selectionView) {
    this.textBox = selectionView.addDescriptionBox(resources.getString("Merits.DescriptionLabel"));
    textBox.addTextChangedListener(model::setCurrentDescription);
  }

  private Tool addCommitTool(OptionalTraitEntryView selectionView) {
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(model::commitSelection);
    return tool;
  }

  private void reset() {
    traitBox.clearSelection();
    textBox.clear();
    model.resetCurrentEntry();
  }

  private void refreshTraitList() {
    traitBox.setObjects(model.getCurrentTraitOptions());
  }
}