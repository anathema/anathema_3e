package net.sf.anathema.hero.equipment.sheet.content.stats.weapon;

import net.sf.anathema.lib.util.Identifier;

public interface IArmourStats extends IEquipmentStats {

  Integer getMobilityPenalty();

  Integer getHardness();

  Integer getSoak();

  Identifier[] getTags();
}