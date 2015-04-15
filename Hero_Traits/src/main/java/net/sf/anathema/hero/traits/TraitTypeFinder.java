package net.sf.anathema.hero.traits;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AttributeType;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Essence;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Willpower;

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
    if (Essence.getId().equals(value)) {
      return Essence;
    }
    if (Willpower.getId().equals(value)) {
      return Willpower;
    } else {
      return NONE_FOUND;
    }
  }
}