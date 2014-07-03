package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;

public interface IEquipmentPrintModel {
  IArmourStats[] getPrintArmours();

  IArmourStats getEffectivePrintArmour(Resources resources, int lineCount);

  IWeaponStats[] getPrintWeapons(Resources resources);
}