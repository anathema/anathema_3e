package net.sf.anathema.hero.equipment.sheet.content.stats.weapon;

import net.sf.anathema.hero.framework.library.IStats;
import net.sf.anathema.library.identifier.Identifier;

public interface IEquipmentStats extends IStats, Identifier {
  boolean representsItemForUseInCombat();
}