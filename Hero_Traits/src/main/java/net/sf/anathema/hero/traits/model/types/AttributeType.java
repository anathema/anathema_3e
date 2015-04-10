package net.sf.anathema.hero.traits.model.types;

import net.sf.anathema.hero.traits.model.TraitType;

public enum AttributeType implements TraitType {

  Strength, Dexterity, Stamina,
  Charisma, Manipulation, Appearance,
  Perception, Intelligence, Wits;

  @Override
  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}