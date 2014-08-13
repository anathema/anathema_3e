package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.state.DefaultTraitStateType;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.types.ITraitTypeVisitor;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

public class TraitStateFetcher {
  private final Hero hero;

  public TraitStateFetcher(Hero hero) {
    this.hero = hero;
  }

  public TraitStateType fetch(TraitType type) {
    TraitStateType[] state = new TraitStateType[1];
    type.accept(new ITraitTypeVisitor() {

      @Override
      public void visitAbility(AbilityType type) {
        AbilitiesModel abilities = AbilitiesModelFetcher.fetch(hero);
        state[0] = abilities.getState(type).getType();
      }

      @Override
      public void visitAttribute(AttributeType type) {
        state[0] = DefaultTraitStateType.Default;
      }

      @Override
      public void visitEssence(OtherTraitType type) {
        state[0] = DefaultTraitStateType.Default;
      }

      @Override
      public void visitWillpower(OtherTraitType type) {
        state[0] = DefaultTraitStateType.Default;
      }

    });
    return state[0];
  }
}
