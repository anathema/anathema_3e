package net.sf.anathema.hero.application.persistence;

import net.sf.anathema.hero.individual.model.Hero;

public interface CharacterInitializer {
  void initialize(Hero hero);
}