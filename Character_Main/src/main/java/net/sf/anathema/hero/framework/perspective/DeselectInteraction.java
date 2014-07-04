package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.library.event.ChangeListener;

public class DeselectInteraction implements ChangeListener {
  private ToggleTool interaction;

  public DeselectInteraction(ToggleTool interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.deselect();
  }
}