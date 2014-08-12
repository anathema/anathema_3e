package net.sf.anathema.hero.merits.display;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dependencies.Produces;

@Produces(MeritsView.class)
public class MeritsViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxMeritsView();
  }
}