package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.hero.elsewhere.experience.ExperienceChange;
import net.sf.anathema.hero.framework.library.overview.OverviewCategory;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.model.RemovableEntryListener;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.library.view.RemovableEntryView;
import net.sf.anathema.library.view.StyledValueView;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.HashMap;
import java.util.Map;

import static java.text.MessageFormat.format;

public class IntimaciesPresenter {

  private final IntimaciesView view;
  private final Resources resources;
  private final IntimaciesModel model;

  private final Map<Intimacy, RemovableEntryView> viewsByEntry = new HashMap<>();

  public IntimaciesPresenter(IntimaciesModel model, IntimaciesView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String labelText = resources.getString("Intimacies.SelectionLabel");
    IntimacyEntryView selectionView = view.addSelectionView(labelText);
    Tool tool = initCreationViewListening(selectionView);
    initOverviewView();
    initModelListening(selectionView, tool);
    for (Intimacy intimacy : model.getEntries()) {
      addSubView(intimacy);
    }
    reset(selectionView);
  }

  private void initOverviewView() {
    final OverviewCategory creationOverview = view.addOverview(resources.getString("Intimacies.Overview.BorderLabel"));
    final StyledValueView<Integer> totalIntimaciesView = creationOverview.addIntegerValueView(
            resources.getString("Intimacies.Overview.Maximum"), 2);
    final OverviewCategory experienceOverview = view.addOverview(
            resources.getString("Intimacies.Overview.BorderLabel"));
    final StyledValueView<Integer> experienceMaximumView = experienceOverview.addIntegerValueView(
            resources.getString("Intimacies.Overview.Maximum"), 2);
    model.addModelChangeListener(() -> recalculateOverview(totalIntimaciesView, experienceMaximumView));
    model.addModelChangeListener(new RemovableEntryListener<Intimacy>() {
      @Override
      public void entryAdded(Intimacy entry) {
        recalculateOverview(totalIntimaciesView, experienceMaximumView);
      }

      @Override
      public void entryAllowed(boolean complete) {
        // Nothing to do
      }

      @Override
      public void entryRemoved(Intimacy entry) {
        recalculateOverview(totalIntimaciesView, experienceMaximumView);
      }
    });
    model.addChangeListener(flavor -> {
      if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
        setOverview(model.isCharacterExperienced(), experienceOverview, creationOverview);
      }
    });
    setOverview(model.isCharacterExperienced(), experienceOverview, creationOverview);
    recalculateOverview(totalIntimaciesView, experienceMaximumView);
  }

  private void setOverview(boolean experienced, OverviewCategory experienceOverview,
                           OverviewCategory creationOverview) {
    if (experienced) {
      view.setOverview(experienceOverview);
    } else {
      view.setOverview(creationOverview);
    }
  }

  private void recalculateOverview(StyledValueView<Integer> totalIntimaciesView,
                                   StyledValueView<Integer> experienceMaximumView) {
    totalIntimaciesView.setValue(model.getEntries().size());
    experienceMaximumView.setValue(model.getEntries().size());
  }

  private void addSubView(Intimacy intimacy) {
    RemovableEntryView subView = createSubView(intimacy);
    viewsByEntry.put(intimacy, subView);
    subView.addButtonListener(() -> model.removeEntry(intimacy));
  }

  private RemovableEntryView createSubView(final Intimacy intimacy) {
    String representation = format("{0}, a {1} {2} {3}", intimacy.getName(), intimacy.getStrength(), intimacy.getOutlook(), intimacy.getBond());
    return view.addIntimacy(representation, new BasicUi().getRemoveIconPath());
  }

  protected void initModelListening(final IntimacyEntryView selectionView, final Tool tool) {
    model.addModelChangeListener(new RemovableEntryListener<Intimacy>() {
      @Override
      public void entryAdded(Intimacy intimacy) {
        addSubView(intimacy);
        reset(selectionView);
      }

      @Override
      public void entryRemoved(Intimacy intimacy) {
        RemovableEntryView traitView = viewsByEntry.get(intimacy);
        traitView.delete();
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

  private Tool initCreationViewListening(IntimacyEntryView selectionView) {
    selectionView.addTextChangeListener(model::setCurrentName);
    allowSelection(selectionView, model.getStrengths(), model::setCurrentStrength, model.getStrength());
    allowSelection(selectionView, model.getOutlooks(), model::setCurrentOutlook, model.getOutlook());
    allowSelection(selectionView, model.getBonds(), model::setCurrentBond, model.getBond());
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(model::commitSelection);
    return tool;
  }

  private <T> void allowSelection(IntimacyEntryView selectionView, T[] objects, ObjectChangedListener<T> listener, T initial) {
    ObjectSelectionView<T> selection = selectionView.addSelection();
    selection.setObjects(objects);
    selection.setSelectedObject(initial);
    selection.addObjectSelectionChangedListener(listener);
  }

  private void reset(IntimacyEntryView selectionView) {
    selectionView.clear();
    model.setCurrentName(null);
  }
}