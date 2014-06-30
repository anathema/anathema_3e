package net.sf.anathema.charm.old.cost;

import net.sf.anathema.lib.util.Identifier;

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