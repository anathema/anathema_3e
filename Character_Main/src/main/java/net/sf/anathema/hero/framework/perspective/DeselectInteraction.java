package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.interaction.model.ToggleTool;

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