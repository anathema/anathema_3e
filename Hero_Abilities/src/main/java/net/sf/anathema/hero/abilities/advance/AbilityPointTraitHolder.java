package net.sf.anathema.hero.abilities.advance;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.traits.display.Traits;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateType;

public class AbilityPointTraitHolder implements PointCalculationTraitHolder{
  private AbilitiesModel abilitiesModel;

  public AbilityPointTraitHolder(AbilitiesModel abilitiesModel) {
    this.abilitiesModel = abilitiesModel;
  }

  @Override
  public TraitState getState(Trait trait) {
    return abilitiesModel.getState(trait);
  }

  @Override
  public Traits getAll() {
    return abilitiesModel.getAll();
  }

  @Override
  public Iterable<TraitStateType> getAvailableTraitStates() {
    return abilitiesModel.getAvailableTraitStates();
  }

  @Override
  public boolean contains(Trait trait) {
    return abilitiesModel.contains(trait.getType());
  }
}
