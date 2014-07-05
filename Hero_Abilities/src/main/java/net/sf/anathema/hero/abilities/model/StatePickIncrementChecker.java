package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
import net.sf.anathema.hero.traits.model.state.TraitStateModel;

import java.util.Map;

public class StatePickIncrementChecker implements MappableTypeIncrementChecker<TraitState> {

  private final Map<TraitState, Integer> stateLimits;
  private final TraitType[] traitTypes;
  private TraitStateMap stateModelMap;

  public StatePickIncrementChecker(Map<TraitState, Integer> stateLimits, TraitType[] traitTypes, TraitStateMap stateModel) {
    this.traitTypes = traitTypes;
    this.stateModelMap = stateModel;
    this.stateLimits = stateLimits;
  }

  @Override
  public boolean isValidIncrement(TraitState state, int increment) {
    Integer limit = stateLimits.get(state);
    if (limit == null) {
    	return true;
    }
    Count<TraitStateModel> countCountsAs = new Count<>(model -> model.getType().countsAs(state));
    stateModelMap.forEach(countCountsAs);
    return countCountsAs.count + increment <= limit;
  }
}