package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.Map;

public class StatePickIncrementChecker implements MappableTypeIncrementChecker<TraitState> {

  private final Map<TraitState, Integer> stateLimits;
  private final TraitType[] traitTypes;
  private final TraitMap traitMap;

  public StatePickIncrementChecker(Map<TraitState, Integer> stateLimits, TraitType[] traitTypes, TraitMap traitMap) {
    this.traitTypes = traitTypes;
    this.traitMap = traitMap;
    this.stateLimits = stateLimits;
  }

  @Override
  public boolean isValidIncrement(TraitState state, int increment) {
    Integer limit = stateLimits.get(state);
    if (limit == null) {
    	return true;
    }
    
	int count = 0;
    for (Trait trait : getAllTraits()) {
      if (trait.getFavorization().getType().countsAs(state)) {
        count++;
      }
    }
    
    return count + increment <= limit;
  }

  private Trait[] getAllTraits() {
    return traitMap.getTraits(traitTypes);
  }
}