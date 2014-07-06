package net.sf.anathema.hero.traits.model.rules.minimum;

import net.sf.anathema.hero.traits.model.TraitType;

import java.util.HashMap;
import java.util.Map;

public class DynamicMinimumMap {

  private final Map<TraitType, AggregatedDynamicMinimum> minimumMap = new HashMap<>();

  public void addMinimum(TraitType traitType, DynamicMinimum minimum) {
    getCachedCompositeFor(traitType).addMinimum(minimum);
  }

  public DynamicMinimum getMinimum(TraitType traitType) {
    return getCachedCompositeFor(traitType);
  }

  private AggregatedDynamicMinimum getCachedCompositeFor(TraitType traitType) {
    if (!minimumMap.containsKey(traitType)) {
      minimumMap.put(traitType, new AggregatedDynamicMinimum());
    }
    return minimumMap.get(traitType);
  }
}
