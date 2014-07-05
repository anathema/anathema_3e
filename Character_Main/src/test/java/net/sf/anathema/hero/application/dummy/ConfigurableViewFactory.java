package net.sf.anathema.hero.application.dummy;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dependencies.Produces;
import net.sf.anathema.library.initialization.DoNotInstantiateAutomatically;

@DoNotInstantiateAutomatically
@Produces(DummyView.class)
public class ConfigurableViewFactory implements SubViewFactory {
  private DummyView view;

  public ConfigurableViewFactory(DummyView view) {
    this.view = view;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) view;
  }
}