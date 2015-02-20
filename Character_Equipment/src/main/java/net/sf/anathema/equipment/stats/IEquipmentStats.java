package net.sf.anathema.equipment.stats;

import net.sf.anathema.hero.sheet.pdf.content.stats.IStats;
import net.sf.anathema.library.identifier.Identifier;

public interface IEquipmentStats extends IStats, Identifier {
  boolean representsItemForUseInCombat();
}