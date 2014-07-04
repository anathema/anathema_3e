package net.sf.anathema.hero.traits.model;

import net.sf.anathema.library.identifier.Identifier;

public enum FavorableState implements Identifier {

  Default, Favored, Caste, Supernal;

  @Override
  public String getId() {
    return name();
  }
}