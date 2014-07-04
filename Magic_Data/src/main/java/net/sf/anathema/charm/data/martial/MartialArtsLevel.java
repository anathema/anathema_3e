package net.sf.anathema.charm.data.martial;

import net.sf.anathema.library.identifier.Identifier;

public enum MartialArtsLevel implements Identifier {
  Mortal, Terrestrial, Celestial, Sidereal;

  @Override
  public String getId() {
    return name();
  }
}