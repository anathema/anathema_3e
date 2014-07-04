package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.individual.model.Hero;

public interface TraitLimitation {

  int getAbsoluteLimit(Hero hero);

  int getCurrentMaximum(Hero hero, boolean modified);
}