package net.sf.anathema.hero.merits.display;

import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritCategory;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.model.RemovableEntryListener;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeritsPresenter {

  private ObjectSelectionView<MeritOption> meritBox;
  private final MeritsView view;
  private final Resources resources;
  private final MeritsModel model;

  private final Map<Merit, MeritItemView> viewsByEntry = new HashMap<>();

  public MeritsPresenter(MeritsModel model, MeritsView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    MeritEntryView selectionView = view.addSelectionView();
    Tool tool = initCreationView(selectionView);
    initModelListening(selectionView, tool);
    for (Merit merit : model.getMerits()) {
      addSubView(merit);
    }
    reset(selectionView);
    List<Trait> meritTraits = model.getContingentTraits();
    for (Trait trait : meritTraits) {
      trait.addCurrentValueListener(value -> refreshMeritList());
    }
  }

  private void addSubView(Merit merit) {
    MeritItemView subView = view.addMerit(merit.toString(), new BasicUi().getRemoveIconPath());
    new TraitPresenter(merit, subView.getIntValueView()).initPresentation();
    viewsByEntry.put(merit, subView);
    subView.addButtonListener(() -> model.removeEntry(merit));
  }

  private void initModelListening(MeritEntryView selectionView, Tool tool) {
    model.addModelChangeListener(new RemovableEntryListener<Merit>() {
      @Override
      public void entryAdded(Merit merit) {
        addSubView(merit);
        reset(selectionView);
      }

      @Override
      public void entryRemoved(Merit merit) {
        MeritItemView itemView = viewsByEntry.get(merit);
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

  private void refreshMeritList() {
    meritBox.setObjects(model.getCurrentMeritOptions());
  }

  private Tool initCreationView(MeritEntryView selectionView) {
    String labelText = resources.getString("Merits.DescriptionLabel");
    ObjectSelectionView<MeritCategory> typeBox = addTypeSelection(selectionView, MeritCategory.values(), model::setCurrentType, model.getCurrentType(), new MeritUiConfiguration<>());
    typeBox.addObjectSelectionChangedListener(item -> refreshMeritList());
    MeritOption initialSelection = model.getCurrentMeritOption() != null ? model.getCurrentMeritOption() : null;
    meritBox = addMeritSelection(selectionView, model.getCurrentMeritOptions(), model::setCurrentMeritOption, initialSelection, new MeritUiConfiguration<>());
    meritBox.addObjectSelectionChangedListener(model::setCurrentMeritOption);
    selectionView.addDescriptionBox(labelText);
    selectionView.addTextChangeListener(model::setCurrentDescription);
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(model::commitSelection);
    return tool;
  }

  private ObjectSelectionView<MeritOption> addMeritSelection(MeritEntryView selectionView, List<MeritOption> options, ObjectChangedListener<MeritOption> listener, MeritOption initial, AgnosticUIConfiguration<MeritOption> uiConfiguration) {
    ObjectSelectionView<MeritOption> view = selectionView.addMeritSelection(uiConfiguration);
    MeritOption[] optionsArray = options.toArray(new MeritOption[options.size()]);
    return allowSelection(view, optionsArray, listener, initial);
  }

  private ObjectSelectionView<MeritCategory> addTypeSelection(MeritEntryView selectionView, MeritCategory[] objects, ObjectChangedListener<MeritCategory> listener, MeritCategory initial, AgnosticUIConfiguration<MeritCategory> uiConfiguration) {
    ObjectSelectionView<MeritCategory> view = selectionView.addSelection(uiConfiguration);
    return allowSelection(view, objects, listener, initial);
  }

  private <T> ObjectSelectionView<T> allowSelection(ObjectSelectionView<T> selection, T[] objects, ObjectChangedListener<T> listener, T initial) {
    selection.setObjects(objects);
    selection.setSelectedObject(initial);
    selection.addObjectSelectionChangedListener(listener);
    return selection;
  }

  private void reset(MeritEntryView selectionView) {
    selectionView.clear();
    model.resetCurrentMerit();
  }

  private class MeritUiConfiguration<T> extends AbstractUIConfiguration<T> {

    @Override
    public String getLabel(T value) {
      return value != null ? value.toString() : null;
    }
  }
}