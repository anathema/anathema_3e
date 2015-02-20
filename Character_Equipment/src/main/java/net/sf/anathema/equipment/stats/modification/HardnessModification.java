package net.sf.anathema.equipment.stats.modification;

public class HardnessModification implements StatsModification {

  private final StatModifier modifier;

  public HardnessModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int original) {
    int bonus = modifier.calculate();
    return original + bonus;
  }
}