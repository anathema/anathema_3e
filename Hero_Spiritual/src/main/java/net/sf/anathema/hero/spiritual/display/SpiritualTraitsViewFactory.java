package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.framework.display.SubViewFactory;

@Produces(SpiritualTraitsView.class)
public class SpiritualTraitsViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxSpiritualTraitsView();
  }
}