package net.sf.anathema.hero.application.dummy;

import net.sf.anathema.hero.application.creation.models.ModelFactoryCollector;
import net.sf.anathema.hero.individual.model.HeroModelFactory;

import java.util.ArrayList;
import java.util.Collection;

public class DummyFactoryCollector implements ModelFactoryCollector {
  private final ArrayList<HeroModelFactory> models = new ArrayList<>();

  public void addFactory(HeroModelFactory modelFactory){
    models.add(modelFactory);
  }

  @Override
  public Collection<HeroModelFactory> collect() {
    return models;
  }

}
