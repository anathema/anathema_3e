package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.library.identifier.Identifier;

public enum AbilityGroupType implements Identifier {

  War, Life, Wisdom;

  @Override
  public String getId() {
    return name();
  }
}