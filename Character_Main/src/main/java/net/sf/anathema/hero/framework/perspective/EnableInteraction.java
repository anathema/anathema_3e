package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.interaction.model.Tool;

public class EnableInteraction implements ChangeListener {
  private Tool interaction;

  public EnableInteraction(Tool interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.enable();
  }
}