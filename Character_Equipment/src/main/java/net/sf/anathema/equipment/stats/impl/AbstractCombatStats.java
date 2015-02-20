package net.sf.anathema.equipment.stats.impl;

public abstract class AbstractCombatStats extends AbstractStats {

  @Override
  public boolean representsItemForUseInCombat() {
    return true;
  }
}