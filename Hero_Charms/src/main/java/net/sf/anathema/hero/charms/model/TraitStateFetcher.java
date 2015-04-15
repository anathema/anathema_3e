package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.state.DefaultTraitStateType;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.types.AbilityType;

public class TraitStateFetcher {
  private final Hero hero;

  public TraitStateFetcher(Hero hero) {
    this.hero = hero;
  }

  public TraitStateType fetch(TraitType type) {
    //TODO (Urs, for Martial Arts): Find another way of recognizing an ability
    if (!(type instanceof AbilityType)) {
      return DefaultTraitStateType.Default;
    }
    AbilitiesModel abilities = AbilitiesModelFetcher.fetch(hero);
    return abilities.getState(type).getType();
  }
}