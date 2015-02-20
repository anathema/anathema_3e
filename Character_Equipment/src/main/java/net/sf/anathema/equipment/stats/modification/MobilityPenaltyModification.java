package net.sf.anathema.equipment.stats.modification;

public class MobilityPenaltyModification implements StatsModification {

  private final StatModifier modifier;

  public MobilityPenaltyModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int original) {
    int bonus = modifier.calculate();
    return Math.min(0, original - bonus);
  }
}