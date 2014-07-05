package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dependencies.Produces;

@Produces(SpiritualTraitsView.class)
public class SpiritualTraitsViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxSpiritualTraitsView();
  }
}