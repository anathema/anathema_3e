package net.sf.anathema.hero.application.persistence;

import net.sf.anathema.library.initialization.ObjectFactory;

import java.util.Collection;

public class HeroModelPersisterAutoCollector {

  private ObjectFactory objectFactory;

  public HeroModelPersisterAutoCollector(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public Collection<HeroModelPersister> collect() {
    return objectFactory.instantiateAllImplementers(HeroModelPersister.class);
  }
}