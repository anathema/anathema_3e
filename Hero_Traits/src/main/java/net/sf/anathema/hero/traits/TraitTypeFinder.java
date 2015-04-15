package net.sf.anathema.hero.traits;

import net.sf.anathema.hero.traits.model.TraitType;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Attributes;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.SpiritualTraits;

public class TraitTypeFinder {

  public static final TraitType NONE_FOUND = null;

  public TraitType getTrait(String value) {
    TraitType trait = getAbilityType(value);
    trait = trait == NONE_FOUND ? getAttributeType(value) : trait;
    trait = trait == NONE_FOUND ? getOtherType(value) : trait;
    return trait;
  }

  private TraitType getAbilityType(String value) {
    try {
      return new TraitTypeFinder().getTrait(value);
    } catch (Exception e) {
      return NONE_FOUND;
    }
  }

  private TraitType getAttributeType(String value) {
    for (TraitType trait : Attributes) {
      if (trait.getId().equals(value)) {
        return trait;
      }
    }
    return NONE_FOUND;
  }

  private TraitType getOtherType(String value) {
    for (TraitType trait : SpiritualTraits) {
      if (trait.getId().equals(value)) {
        return trait;
      }
    }
    return NONE_FOUND;
  }
}