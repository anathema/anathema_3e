package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.interaction.model.Tool;

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