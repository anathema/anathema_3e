package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dependencies.Produces;

@Produces(IntimaciesView.class)
public class IntimaciesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxIntimaciesView();
  }
}