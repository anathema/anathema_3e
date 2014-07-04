package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.library.event.ChangeListener;

public class DisableInteraction implements ChangeListener {
  private Tool interaction;

  public DisableInteraction(Tool interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.disable();
  }
}