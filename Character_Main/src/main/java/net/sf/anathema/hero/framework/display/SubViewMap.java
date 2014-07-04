package net.sf.anathema.hero.framework.display;

import net.sf.anathema.framework.util.ReflectionFactoryMap;
import net.sf.anathema.library.initialization.ObjectFactory;

public class SubViewMap implements SubViewRegistry {

  private final ReflectionFactoryMap<SubViewFactory> factories;

  public SubViewMap(ObjectFactory objectFactory) {
    factories = new ReflectionFactoryMap<>(objectFactory, SubViewFactory.class);
  }

  public <T> T get(Class<T> viewClass) {
    return factories.get(viewClass).create();
  }
}