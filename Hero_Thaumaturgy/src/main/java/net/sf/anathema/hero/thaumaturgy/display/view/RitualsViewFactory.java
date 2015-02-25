package net.sf.anathema.hero.thaumaturgy.display.view;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.hero.thaumaturgy.display.presenter.RitualsView;
import net.sf.anathema.library.dependencies.Produces;

@Produces(RitualsView.class)
public class RitualsViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxRitualsView();
  }
}