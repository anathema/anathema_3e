package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.interaction.model.ToggleTool;

public class SelectInteraction implements ChangeListener {
  private ToggleTool interaction;

  public SelectInteraction(ToggleTool interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.select();
  }
}