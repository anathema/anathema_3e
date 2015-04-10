package net.sf.anathema.hero.traits.model.types;

import net.sf.anathema.hero.traits.model.TraitType;

public enum AbilityType implements TraitType {
  Archery,
  Brawl,
  MartialArts,
  Melee,
  Thrown,
  War,
  Integrity,
  Performance,
  Presence,
  Resistance,
  Survival,
  Craft,
  Investigation,
  Lore,
  Medicine,
  Occult,
  Athletics,
  Awareness,
  Dodge,
  Larceny,
  Stealth,
  Bureaucracy,
  Linguistics,
  Ride,
  Sail,
  Socialize;

  @Override
  public String getId() {
    return name();
  }
}