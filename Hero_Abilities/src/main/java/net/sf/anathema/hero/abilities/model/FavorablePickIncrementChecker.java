package net.sf.anathema.hero.abilities.model;

import java.util.Map;

import net.sf.anathema.hero.traits.model.FavorableState;
import net.sf.anathema.hero.traits.model.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitType;

public class FavorablePickIncrementChecker implements MappableTypeIncrementChecker<FavorableState> {

  private final Map<FavorableState, Integer> stateLimits;
  private final TraitType[] traitTypes;
  private final TraitMap traitMap;

  public FavorablePickIncrementChecker(Map<FavorableState, Integer> stateLimits, TraitType[] traitTypes, TraitMap traitMap) {
    this.traitTypes = traitTypes;
    this.traitMap = traitMap;
    this.stateLimits = stateLimits;
  }

  @Override
  public boolean isValidIncrement(FavorableState state, int increment) {
    Integer limit = stateLimits.get(state);
    if (limit == null) {
    	return true;
    }
    
	int count = 0;
    for (Trait trait : getAllTraits()) {
      if (trait.getFavorization().getFavorableState().countsAs(state)) {
        count++;
      }
    }
    
    return count + increment <= limit;
  }

  private Trait[] getAllTraits() {
    return traitMap.getTraits(traitTypes);
  }
}