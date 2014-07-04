package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.ItemSelectionModel;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

public class ExperiencedInteractionPresenter {
  private ItemSelectionModel model;
  private ToggleTool interaction;
  private Resources resources;

  public ExperiencedInteractionPresenter(ItemSelectionModel model, ToggleTool interaction, Resources resources) {
    this.model = model;
    this.interaction = interaction;
    this.resources = resources;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeToggling();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setIcon(new RelativePath("icons/ToolXp.png"));
    interaction.setTooltip(resources.getString("CharacterTool.ToExperienced.Tooltip"));
  }

  private void initializeEnabling() {
    model.whenGetsSelection(new EnableInteraction(interaction));
    interaction.disable();
  }

  private void initializeToggling() {
    model.whenCurrentSelectionBecomesInexperienced(new DeselectInteraction(interaction));
    model.whenCurrentSelectionBecomesExperienced(new SelectInteraction(interaction));
  }

  private void initializeCommand() {
    interaction.setCommand(() -> {
      model.convertCurrentToExperienced();
      interaction.select();
    });
  }
}