package net.sf.anathema.hero.application;

import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.platform.initialization.ReflectionFactoryMap;

public class SubViewMap implements SubViewRegistry {

  private final ReflectionFactoryMap<SubViewFactory> factories;

  public SubViewMap(ObjectFactory objectFactory) {
    factories = new ReflectionFactoryMap<>(objectFactory, SubViewFactory.class);
  }

  public <T> T get(Class<T> viewClass) {
    return factories.get(viewClass).create();
  }
}