package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.hero.framework.display.SubViewFactory;
import net.sf.anathema.platform.initialization.Produces;

@Produces(SpiritualTraitsView.class)
public class SpiritualTraitsViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxSpiritualTraitsView();
  }
}