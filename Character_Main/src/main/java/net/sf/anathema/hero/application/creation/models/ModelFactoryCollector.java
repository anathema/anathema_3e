package net.sf.anathema.hero.application.creation.models;

import net.sf.anathema.hero.individual.model.HeroModelFactory;

import java.util.Collection;

public interface ModelFactoryCollector {
  Collection<HeroModelFactory> collect();
}