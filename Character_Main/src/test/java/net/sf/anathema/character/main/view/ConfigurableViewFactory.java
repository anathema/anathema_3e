package net.sf.anathema.character.main.view;

import net.sf.anathema.hero.framework.display.SubViewFactory;
import net.sf.anathema.library.initialization.DoNotInstantiateAutomatically;
import net.sf.anathema.platform.initialization.Produces;

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