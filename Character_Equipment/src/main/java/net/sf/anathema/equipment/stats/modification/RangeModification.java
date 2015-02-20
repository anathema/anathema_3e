package net.sf.anathema.equipment.stats.modification;

public class RangeModification implements StatsModification {

  private final StatModifier modifier;

  public RangeModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int input) {
    int bonus = modifier.calculate();
    return input + bonus;
  }
}