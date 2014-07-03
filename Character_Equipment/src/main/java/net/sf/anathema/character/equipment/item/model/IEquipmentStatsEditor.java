package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

import java.awt.Component;

public interface IEquipmentStatsEditor {

  IEquipmentStats editStats(Component parentComponent, Resources resources, String[] nameArray, IEquipmentStats selectedStats);

}
