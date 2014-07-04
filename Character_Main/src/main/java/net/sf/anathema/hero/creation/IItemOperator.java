package net.sf.anathema.hero.creation;

import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.exception.PersistenceException;

public interface IItemOperator {

  void operate(HeroSplat template)
      throws PersistenceException;
}