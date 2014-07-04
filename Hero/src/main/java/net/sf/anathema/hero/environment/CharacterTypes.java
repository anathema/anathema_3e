package net.sf.anathema.hero.environment;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.individual.splat.CharacterType;

public interface CharacterTypes extends Iterable<CharacterType>, ExtensibleDataSet {
  CharacterType findById(String id);
}
