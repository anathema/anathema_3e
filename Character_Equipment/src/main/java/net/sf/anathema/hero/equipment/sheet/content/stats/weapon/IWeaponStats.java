package net.sf.anathema.hero.equipment.sheet.content.stats.weapon;

import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.lib.util.Identifier;

import java.util.Collection;

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

  IEquipmentStats[] getViews();

  int getOverwhelmingValue();
}