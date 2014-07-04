package net.sf.anathema.points.display.experience;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;
import net.sf.anathema.points.model.xp.ExperiencePoints;

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
    experienceView.addSelectionListener(experiencePoints::selectForChange);
    configureAddTool();
    configureRemoveTool();
    experiencePoints.addExperiencePointConfigurationListener(this::refreshEntriesInView);
    experiencePoints.addEntrySelectionListener(this::updateSelectionInView);
    experienceView.initGui(new DefaultExperienceProperties(resources));
    refreshEntriesInView();
    experienceView.addUpdateListener((points, description) -> experiencePoints.updateCurrentSelection(description, points));
    updateTotal();
  }

  private void configureAddTool() {
    Tool tool = experienceView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(experiencePoints::addEntry);
  }


  private void configureRemoveTool() {
    final Tool tool = experienceView.addTool();
    tool.setIcon(new BasicUi().getRemoveIconPath());
    tool.setCommand(experiencePoints::removeEntry);
    experiencePoints.addEntrySelectionListener(entry -> {
      if (entry != null) {
        tool.enable();
      } else {
        tool.disable();
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