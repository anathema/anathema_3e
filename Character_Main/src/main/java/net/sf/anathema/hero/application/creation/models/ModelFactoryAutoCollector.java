package net.sf.anathema.hero.application.creation.models;

import net.sf.anathema.hero.application.creation.HeroModelFactory;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.util.Collection;

public class ModelFactoryAutoCollector implements ModelFactoryCollector {

  private ObjectFactory objectFactory;

  public ModelFactoryAutoCollector(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public Collection<HeroModelFactory> collect() {
    return objectFactory.instantiateAllImplementers(HeroModelFactory.class);
  }
}