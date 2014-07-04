package net.sf.anathema.hero.environment.herotype;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.individual.splat.CharacterType;

public interface HeroTypes extends Iterable<CharacterType>, ExtensibleDataSet {
  CharacterType findById(String id);
}
