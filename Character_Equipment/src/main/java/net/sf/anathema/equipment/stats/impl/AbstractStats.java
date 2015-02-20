package net.sf.anathema.equipment.stats.impl;

import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.identifier.Identifier;

public abstract class AbstractStats implements IEquipmentStats {

  private Identifier name;

  @Override
  public Identifier getName() {
    return name;
  }

  public final void setName(Identifier name) {
    this.name = name;
  }
}