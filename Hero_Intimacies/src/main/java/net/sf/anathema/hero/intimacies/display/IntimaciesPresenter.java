package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.framework.CharacterUI;
import net.sf.anathema.character.framework.display.labelledvalue.IValueView;
import net.sf.anathema.character.framework.library.overview.OverviewCategory;
import net.sf.anathema.character.framework.library.removableentry.RemovableEntryListener;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.display.ExtensibleTraitView;
import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.lib.gui.AgnosticUIConfiguration.NO_ICON;

public class IntimaciesPresenter {

  private final IntimaciesView view;
  private final Resources resources;
  private final IntimaciesModel model;

  private final Map<Intimacy, ExtensibleTraitView> viewsByEntry = new HashMap<>();

  public IntimaciesPresenter(IntimaciesModel model, IntimaciesView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String labelText = resources.getString("Intimacies.SelectionLabel");
    StringEntryView selectionView = view.addSelectionView(labelText);
    Tool tool = initSelectionViewListening(selectionView);
    initOverviewView();
    initModelListening(selectionView, tool);
    for (Intimacy intimacy : model.getEntries()) {
      addSubView(intimacy);
    }
    reset(selectionView);
  }

  private void initOverviewView() {
    final OverviewCategory creationOverview = view.addOverview(resources.getString("Intimacies.Overview.BorderLabel"));
    final IValueView<Integer> totalIntimaciesView = creationOverview.addIntegerValueView(
            resources.getString("Intimacies.Overview.Maximum"), 2);
    final OverviewCategory experienceOverview = view.addOverview(
            resources.getString("Intimacies.Overview.BorderLabel"));
    final IValueView<Integer> experienceMaximumView = experienceOverview.addIntegerValueView(
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

  private void recalculateOverview(IValueView<Integer> totalIntimaciesView,
                                   IValueView<Integer> experienceMaximumView) {
    totalIntimaciesView.setValue(model.getEntries().size());
    experienceMaximumView.setValue(model.getEntries().size());
  }

  private void addSubView(Intimacy v) {
    ExtensibleTraitView subView = createSubView(v);
    viewsByEntry.put(v, subView);
  }

  private ExtensibleTraitView createSubView(final Intimacy intimacy) {
    int maximalValue = model.getCompletionValue();
    int currentValue = intimacy.getTrait().getCurrentValue();
    String name = intimacy.getName();
    ExtensibleTraitView intimacyView = view.addIntimacy(name, currentValue, maximalValue);
    new TraitPresenter(intimacy.getTrait(), intimacyView.getIntValueView()).initPresentation();
    addLinkToggle(intimacyView, intimacy);
    addDeleteTool(intimacyView, intimacy);
    return intimacyView;
  }

  private void addDeleteTool(ExtensibleTraitView extensibleTraitView, final Intimacy intimacy) {
    Tool tool = extensibleTraitView.addToolBehind();
    tool.setIcon(new BasicUi().getRemoveIconPath());
    tool.setCommand(() -> model.removeEntry(intimacy));
  }

  private void addLinkToggle(ExtensibleTraitView extensibleTraitView, final Intimacy intimacy) {
    final ToggleTool toggleTool = extensibleTraitView.addToggleBehind();
    toggleTool.setCommand(() -> intimacy.setComplete(!intimacy.isComplete()));
    intimacy.addCompletionListener(isComplete -> setCompletionState(isComplete, toggleTool));
    setCompletionState(intimacy.isComplete(), toggleTool);
  }


  private void setCompletionState(boolean isComplete, ToggleTool toggleTool) {
    if (isComplete) {
      toggleTool.select();
      toggleTool.setIcon(new CharacterUI().getLinkIconPath());
    } else {
      toggleTool.deselect();
      toggleTool.setIcon(NO_ICON);
    }
  }

  protected void initModelListening(final StringEntryView selectionView, final Tool tool) {
    model.addModelChangeListener(new RemovableEntryListener<Intimacy>() {
      @Override
      public void entryAdded(Intimacy v) {
        addSubView(v);
        reset(selectionView);
      }

      @Override
      public void entryRemoved(Intimacy v) {
        ExtensibleTraitView traitView = viewsByEntry.get(v);
        traitView.remove();
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

  protected final Tool initSelectionViewListening(StringEntryView selectionView) {
    selectionView.addTextChangeListener(model::setCurrentName);
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(model::commitSelection);
    return tool;
  }

  protected final void reset(StringEntryView selectionView) {
    selectionView.clear();
    model.setCurrentName(null);
  }
}