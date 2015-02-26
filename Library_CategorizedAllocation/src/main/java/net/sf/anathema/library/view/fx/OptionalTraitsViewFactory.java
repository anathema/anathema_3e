package net.sf.anathema.library.view.fx;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dependencies.Produces;
import net.sf.anathema.library.view.OptionalTraitsView;

@Produces(OptionalTraitsView.class)
public class OptionalTraitsViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxOptionalTraitsView();
  }
}