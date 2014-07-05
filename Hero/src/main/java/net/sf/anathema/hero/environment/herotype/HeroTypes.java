package net.sf.anathema.hero.environment.herotype;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.individual.splat.HeroType;

public interface HeroTypes extends Iterable<HeroType>, ExtensibleDataSet {
  HeroType findById(String id);
}
