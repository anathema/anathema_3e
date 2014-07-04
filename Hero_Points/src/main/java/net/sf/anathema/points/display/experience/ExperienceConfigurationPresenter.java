package net.sf.anathema.points.display.experience;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;
import net.sf.anathema.points.model.xp.ExperiencePoints;
import net.sf.anathema.points.model.xp.ExperiencePointsListener;
import net.sf.anathema.points.model.xp.ExperienceSelectionListener;

public class ExperienceConfigurationPresenter {

  private final ExperiencePoints experiencePoints;
  private final ExperienceView experienceView;
  private final Resources resources;

  public ExperienceConfigurationPresenter(Resources resources, ExperiencePoints experiencePoints,
                                          ExperienceView experienceView) {
    this.resources = resources;
    this.experiencePoints = experiencePoints;
    this.experienceView = experienceView;
  }

  public void initPresentation() {
    experienceView.addSelectionListener(new ExperienceSelectionListener() {
      @Override
      public void selectionChanged(ExperiencePointEntry entry) {
        experiencePoints.selectForChange(entry);
      }
    });
    configureAddTool();
    configureRemoveTool();
    experiencePoints.addExperiencePointConfigurationListener(new ExperiencePointsListener() {
      @Override
      public void entriesAddedRemovedOrChanged() {
        refreshEntriesInView();
      }
    });
    experiencePoints.addEntrySelectionListener(new ExperienceSelectionListener() {
      @Override
      public void selectionChanged(ExperiencePointEntry entry) {
        updateSelectionInView(entry);
      }
    });
    experienceView.initGui(new DefaultExperienceProperties(resources));
    refreshEntriesInView();
    experienceView.addUpdateListener(new ExperienceUpdateListener() {
      public void update(int points, String description) {
        experiencePoints.updateCurrentSelection(description, points);
      }
    });
    updateTotal();
  }

  private void configureAddTool() {
    Tool tool = experienceView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        experiencePoints.addEntry();
      }
    });
  }


  private void configureRemoveTool() {
    final Tool tool = experienceView.addTool();
    tool.setIcon(new BasicUi().getRemoveIconPath());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        experiencePoints.removeEntry();
      }
    });
    experiencePoints.addEntrySelectionListener(new ExperienceSelectionListener() {
      @Override
      public void selectionChanged(ExperiencePointEntry entry) {
        if (entry != null) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    });
  }

  private void updateSelectionInView(ExperiencePointEntry entry) {
    experienceView.setSelection(entry);
  }

  private void refreshEntriesInView() {
    experienceView.setEntries(experiencePoints.getAllEntries());
    updateSelectionInView(experiencePoints.getCurrentSelection());
    updateTotal();
  }

  private void updateTotal() {
    experienceView.setTotalValueLabel(experiencePoints.getTotalExperiencePoints());
  }
}