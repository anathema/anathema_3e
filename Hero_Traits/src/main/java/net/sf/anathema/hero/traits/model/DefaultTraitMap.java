package net.sf.anathema.hero.traits.model;

import com.google.common.base.Preconditions;
import net.sf.anathema.hero.traits.display.Traits;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultTraitMap implements TraitMap {

  private final Map<TraitType, Trait> traitsByType = new HashMap<>();

  public void addTraits(Trait... traits) {
    for (Trait trait : traits) {
      addSingleTrait(trait);
    }
  }

  public void addTraits(Iterable<Trait> traits) {
    for (Trait trait : traits) {
      addSingleTrait(trait);
    }
  }

  private void addSingleTrait(Trait trait) {
    Preconditions.checkArgument(!contains(trait.getType()), "Trait of type already contained " + trait.getType());
    traitsByType.put(trait.getType(), trait);
  }

  @Override
  public final Trait getTrait(TraitType traitType) {
    if (contains(traitType)) {
      return traitsByType.get(traitType);
    }
    throw new UnsupportedOperationException("Unsupported trait type " + traitType);
  }

  @Override
  public final Traits getTraits(TraitType... traitTypes) {
    Traits foundTraits = new Traits();
    for (TraitType type : traitTypes) {
      foundTraits.add(getTrait(type));
    }
    return foundTraits;
  }

  public final Traits getAll() {
    Collection<Trait> attributes = traitsByType.values();
    return new Traits(attributes);
  }

  public final boolean contains(TraitType traitType) {
    return traitsByType.containsKey(traitType);
  }
}