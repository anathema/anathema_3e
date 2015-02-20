package net.sf.anathema.hero.equipment;

import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.library.resources.Resources;

public interface IEquipmentPrintModel {
  IArmourStats[] getPrintArmours();

  IArmourStats getEffectivePrintArmour(Resources resources, int lineCount);

  IWeaponStats[] getPrintWeapons(Resources resources);
}