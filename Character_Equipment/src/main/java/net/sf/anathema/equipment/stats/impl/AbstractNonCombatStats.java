package net.sf.anathema.equipment.stats.impl;

public abstract class AbstractNonCombatStats extends AbstractStats {
  @Override
  public boolean representsItemForUseInCombat() {
    return false;
  }
}
