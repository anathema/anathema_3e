package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.hero.equipment.EquipmentHeroEvaluator;

public class StatsPresentationFactory {

  private final EquipmentHeroEvaluator evaluator;
  private final IEquipmentItem item;

  public StatsPresentationFactory(EquipmentHeroEvaluator evaluator, IEquipmentItem item) {
    this.evaluator = evaluator;
    this.item = item;
  }

  public StatsPresentationStrategy choosePresentationStrategy(IEquipmentStats equipment) {
      return new DefaultPresentationStrategy(equipment);
  }
}
