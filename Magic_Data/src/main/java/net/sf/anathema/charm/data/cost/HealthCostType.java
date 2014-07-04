package net.sf.anathema.charm.data.cost;

import net.sf.anathema.library.identifier.Identifier;

public enum HealthCostType implements Identifier {
  Bashing, Lethal, Aggravated;

  @Override
  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}