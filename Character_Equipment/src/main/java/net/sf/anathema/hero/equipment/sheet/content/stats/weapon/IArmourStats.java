package net.sf.anathema.hero.equipment.sheet.content.stats.weapon;

import net.sf.anathema.lib.util.Identifier;

import java.util.Collection;

public interface IArmourStats extends IEquipmentStats {

  Integer getMobilityPenalty();

  Integer getHardness();

  Integer getSoak();

  Collection<Identifier> getTags();
}