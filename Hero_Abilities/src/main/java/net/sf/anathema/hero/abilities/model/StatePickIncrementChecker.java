package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateType;

import java.util.Map;

public class StatePickIncrementChecker implements MappableTypeIncrementChecker<TraitStateType> {

  private final Map<TraitStateType, Integer> stateLimits;
  private TraitStateCollection stateCollection;

  public StatePickIncrementChecker(Map<TraitStateType, Integer> stateLimits, TraitStateCollection stateModel) {
    this.stateCollection = stateModel;
    this.stateLimits = stateLimits;
  }

  @Override
  public boolean isValidIncrement(TraitStateType state, int increment) {
    Integer limit = stateLimits.get(state);
    if (limit == null) {
    	return true;
    }
    Count<TraitState> countCountsAs = new Count<>(model -> model.getType().countsAs(state));
    stateCollection.forEach(countCountsAs);
    return countCountsAs.count + increment <= limit;
  }
}