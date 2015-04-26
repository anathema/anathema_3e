package net.sf.anathema.hero.martial.model;

import net.sf.anathema.hero.abilities.advance.PointCalculationTraitHolder;
import net.sf.anathema.hero.traits.display.Traits;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.DefaultTraitStateType;
import net.sf.anathema.hero.traits.model.state.NullTraitState;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.types.CommonTraitTypes;

import java.util.Collections;
import java.util.List;

public class MartialArtsTraitHolder implements PointCalculationTraitHolder {
  public static final List<TraitStateType> This_Model_Does_Not_Contribute_Any_New_States = Collections.emptyList();
  private final MartialArtsModel martialArtsModel;
  private final TraitStateMap states;

  public MartialArtsTraitHolder(MartialArtsModel martialArtsModel, TraitStateMap states) {
    this.martialArtsModel = martialArtsModel;
    this.states = states;
  }

  @Override
  public TraitState getState(Trait trait) {
    return states.getState(CommonTraitTypes.Brawl);
  }

  @Override
  public Traits getAll() {
    Traits traits = new Traits();
    for (Trait trait : martialArtsModel.getLearnedStyles()) {
      traits.add(trait);
    }
    for (Trait trait : martialArtsModel.getAvailableStyles()) {
      traits.add(trait);
    }
    return traits;
  }

  @Override
  public Iterable<TraitStateType> getAvailableTraitStates() {
    return This_Model_Does_Not_Contribute_Any_New_States;
  }

  @Override
  public boolean contains(Trait trait) {
    for (Trait candidate : martialArtsModel.getLearnedStyles()) {
      if(candidate.equals(trait)){
        return true;
      }
    }
    for (Trait candidate : martialArtsModel.getAvailableStyles()) {
      if(candidate.equals(trait)){
        return true;
      }
    }
    return false;
  }
}
