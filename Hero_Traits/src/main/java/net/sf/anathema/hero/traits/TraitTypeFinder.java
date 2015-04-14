package net.sf.anathema.hero.traits;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

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
    try {
      return AttributeType.valueOf(value);
    } catch (Exception e) {
      return NONE_FOUND;
    }
  }

  private TraitType getOtherType(String value) {
    try {
      return OtherTraitType.valueOf(value);
    } catch (Exception e) {
      return NONE_FOUND;
    }
  }
}