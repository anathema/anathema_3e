package net.sf.anathema.hero.thaumaturgy.display.presenter;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.hero.thaumaturgy.model.KnownRitual;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModel;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.model.RemovableEntryListener;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.platform.taskbar.BasicUi;

public class RitualsPresenter {

  private ObjectSelectionView<ThaumaturgyRitual> ritualBox;
  private ITextView textBox;
  private final RitualsView view;
  private final Resources resources;
  private final ThaumaturgyModel model;
  private final Map<KnownRitual, RitualItemView> viewsByEntry = new HashMap<>();

  public RitualsPresenter(ThaumaturgyModel model, RitualsView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    RitualEntryView selectionView = view.addSelectionView();
    Tool tool = initCreationView(selectionView);
    initModelListening(selectionView, tool);
    for (KnownRitual merit : model.getKnownRituals()) {
      addSubView(merit);
    }
    reset();
  }

  private void addSubView(KnownRitual ritual) {
    RitualItemView subView = view.addMerit(ritual.toString(), new BasicUi().getRemoveIconPath());
    new TraitPresenter(ritual, subView.getIntValueView()).initPresentation();
    viewsByEntry.put(ritual, subView);
    subView.addButtonListener(() -> model.removeEntry(ritual));
  }

  private void initModelListening(RitualEntryView selectionView, Tool tool) {
    model.addModelChangeListener(new RemovableEntryListener<KnownRitual>() {
      @Override
      public void entryAdded(KnownRitual ritual) {
        addSubView(ritual);
        refreshRitualsList();
        reset();
      }

      @Override
      public void entryRemoved(KnownRitual ritual) {
        RitualItemView itemView = viewsByEntry.get(ritual);
        refreshRitualsList();
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

  private Tool initCreationView(RitualEntryView selectionView) {
    this.ritualBox = addRitualSelection(selectionView);
    addDescriptionBox(selectionView);
    return addCommitTool(selectionView);
  }

  private ObjectSelectionView<ThaumaturgyRitual> addRitualSelection(RitualEntryView selectionView) {
    ObjectSelectionView<ThaumaturgyRitual> meritView = selectionView.addSelection(new ToStringConfiguration<>());
    meritView.setObjects(model.getCurrentRitualOptions());
    meritView.setSelectedObject(model.getCurrentRitualOption());
    meritView.addObjectSelectionChangedListener(model::setCurrentRitual);
    model.whenCurrentOptionChanges(() -> {
      meritView.setSelectedObject(model.getCurrentRitualOption());
    });
    return meritView;
  }

  private void addDescriptionBox(RitualEntryView selectionView) {
    this.textBox = selectionView.addDescriptionBox(resources.getString("Thaumaturgy.DescriptionLabel"));
    textBox.addTextChangedListener(model::setCurrentDescription);
  }

  private Tool addCommitTool(RitualEntryView selectionView) {
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(model::commitSelection);
    return tool;
  }

  private void reset() {
    ritualBox.clearSelection();
    textBox.clear();
    model.resetCurrentRitual();
  }

  private void refreshRitualsList() {
    ritualBox.setObjects(model.getCurrentRitualOptions());
  }
}