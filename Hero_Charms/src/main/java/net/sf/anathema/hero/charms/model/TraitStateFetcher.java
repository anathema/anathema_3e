package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.state.DefaultTraitStateType;
import net.sf.anathema.hero.traits.model.state.TraitStateType;

public class TraitStateFetcher {
  private final Hero hero;

  public TraitStateFetcher(Hero hero) {
    this.hero = hero;
  }

  public TraitStateType fetch(TraitType type) {
    boolean isAnAbility = AbilitiesModelFetcher.fetch(hero).contains(type);
    if (!isAnAbility) {
      return DefaultTraitStateType.Default;
    }
    AbilitiesModel abilities = AbilitiesModelFetcher.fetch(hero);
    return abilities.getState(type).getType();
  }
}