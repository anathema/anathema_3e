package net.sf.anathema.hero.traits.model.types;

import net.sf.anathema.hero.traits.model.TraitType;

public enum OtherTraitType implements TraitType {
  Essence,
  Willpower;

  @Override
  public String getId() {
    return name();
  }
}