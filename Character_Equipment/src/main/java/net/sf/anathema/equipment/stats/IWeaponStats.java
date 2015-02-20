package net.sf.anathema.equipment.stats;

import java.util.Collection;

import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.identifier.Identifier;

public interface IWeaponStats extends IEquipmentStats {

  int getAccuracy();

  int getDamage();

  HealthType getDamageType();

  Collection<Identifier> getTags();

  TraitType getTraitType();

  Integer getDefence();

  TraitType getDamageTraitType();

  boolean inflictsNoDamage();

  boolean isRangedCombat();
  
  int getMobilityPenalty();

  ItemStatsSet getViews();

  int getOverwhelmingValue();
}