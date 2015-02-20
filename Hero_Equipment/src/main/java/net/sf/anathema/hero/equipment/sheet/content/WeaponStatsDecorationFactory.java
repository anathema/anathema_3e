package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.equipment.stats.impl.WeaponStatsDecorator;
import net.sf.anathema.library.resources.Resources;

public class WeaponStatsDecorationFactory implements IEquipmentStatsDecorationFactory<IWeaponStats> {

  private final EquipmentPrintNameFactory nameFactory;

  public WeaponStatsDecorationFactory(Resources resources) {
    this.nameFactory = new EquipmentPrintNameFactory(resources);
  }

  @Override
  public IWeaponStats createRenamedPrintDecoration(IEquipmentItem item, IWeaponStats stats) {
    return new WeaponStatsDecorator(stats, nameFactory.create(item, stats));
  }
}