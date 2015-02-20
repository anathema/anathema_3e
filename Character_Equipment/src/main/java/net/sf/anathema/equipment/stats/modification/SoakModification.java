package net.sf.anathema.equipment.stats.modification;

public class SoakModification implements StatsModification {

  private final StatModifier modifier;

  public SoakModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int input) {
    int bonus = modifier.calculate();
    return input + bonus;
  }
}