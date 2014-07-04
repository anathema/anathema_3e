package net.sf.anathema.hero.framework.persistence;

import net.sf.anathema.hero.individual.model.Hero;

public interface CharacterInitializer {
  void initialize(Hero hero);
}