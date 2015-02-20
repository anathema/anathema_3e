package net.sf.anathema.equipment.stats;

import java.util.Collection;

import net.sf.anathema.library.identifier.Identifier;

public interface IArmourStats extends IEquipmentStats {

  Integer getMobilityPenalty();

  Integer getHardness();

  Integer getSoak();

  Collection<Identifier> getTags();
}