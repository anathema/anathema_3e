package net.sf.anathema.hero.merits.display.presenter;

import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritCategory;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.model.RemovableEntryListener;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeritsPresenter {

  private ObjectSelectionView<MeritOption> meritBox;
  private ITextView textBox;
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
    reset();
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
        reset();
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

  private Tool initCreationView(MeritEntryView selectionView) {
    addTypeSelection(selectionView);
    this.meritBox = addMeritSelection(selectionView);
    addDescriptionBox(selectionView);
    return addCommitTool(selectionView);
  }

  private void addTypeSelection(MeritEntryView selectionView) {
    ObjectSelectionView<MeritCategory> typeView = selectionView.addSelection(new ToStringConfiguration<>());
    typeView.setObjects(MeritCategory.values());
    typeView.setSelectedObject(model.getCurrentType());
    typeView.addObjectSelectionChangedListener(model::setCurrentType);
    model.whenTypeChanges(() -> {
      typeView.setSelectedObject(model.getCurrentType());
      refreshMeritList();
    });
  }

  private ObjectSelectionView<MeritOption> addMeritSelection(MeritEntryView selectionView) {
    ObjectSelectionView<MeritOption> meritView = selectionView.addSelection(new ToStringConfiguration<>());
    meritView.setObjects(model.getCurrentMeritOptions());
    meritView.setSelectedObject(model.getCurrentMeritOption());
    meritView.addObjectSelectionChangedListener(model::setCurrentMeritOption);
    model.whenCurrentOptionChanges(() -> {
      meritView.setSelectedObject(model.getCurrentMeritOption());
    });
    return meritView;
  }

  private void addDescriptionBox(MeritEntryView selectionView) {
    this.textBox = selectionView.addDescriptionBox(resources.getString("Merits.DescriptionLabel"));
    textBox.addTextChangedListener(model::setCurrentDescription);
  }

  private Tool addCommitTool(MeritEntryView selectionView) {
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(model::commitSelection);
    return tool;
  }

  private void reset() {
    meritBox.clearSelection();
    textBox.clear();
    model.resetCurrentMerit();
  }

  private void refreshMeritList() {
    meritBox.setObjects(model.getCurrentMeritOptions());
  }
}