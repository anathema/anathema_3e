package net.sf.anathema.hero.abilities.advance;

import net.sf.anathema.hero.individual.model.HeroModel;

public interface AbilitiesPointModel extends HeroModel {

  void add(PointCalculationTraitHolder holder);
}
