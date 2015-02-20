package net.sf.anathema.hero.equipment.model;

import java.util.Collection;

import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.equipment.stats.ItemStatsSet;
import net.sf.anathema.equipment.stats.impl.AbstractStats;
import net.sf.anathema.equipment.stats.impl.Proxy;
import net.sf.anathema.equipment.stats.impl.WeaponTag;
import net.sf.anathema.equipment.stats.modification.AccuracyModification;
import net.sf.anathema.equipment.stats.modification.DamageModification;
import net.sf.anathema.equipment.stats.modification.DefenseModification;
import net.sf.anathema.equipment.stats.modification.EquipmentAccuracyModifier;
import net.sf.anathema.equipment.stats.modification.EquipmentDamageModifier;
import net.sf.anathema.equipment.stats.modification.EquipmentDefenceModifier;
import net.sf.anathema.equipment.stats.modification.StatModifier;
import net.sf.anathema.equipment.stats.modification.StatsModification;
import net.sf.anathema.equipment.stats.modification.WeaponStatsType;
import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.identifier.Identifier;

import com.google.common.base.Objects;

public class ProxyWeaponStats extends AbstractStats implements IWeaponStats, Proxy<IWeaponStats> {

  private final IWeaponStats delegate;
  private final ModifierFactory modifiers;

  public ProxyWeaponStats(IWeaponStats stats, ModifierFactory modifiers) {
    this.delegate = stats;
    this.modifiers = modifiers;
  }

  @Override
  public IWeaponStats getUnderlying() {
    return this.delegate;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ProxyWeaponStats)) {
      return delegate != null && delegate.equals(obj);
    }
    ProxyWeaponStats other = (ProxyWeaponStats) obj;
    return Objects.equal(delegate, other.delegate);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  @Override
  public int getAccuracy() {
    int accuracy = delegate.getAccuracy();
    WeaponStatsType type = getWeaponStatsType();
    StatModifier equipment = new EquipmentAccuracyModifier(modifiers.createModifiers(), type);
    return getModifiedValue(new AccuracyModification(equipment), accuracy);
  }

  private WeaponStatsType getWeaponStatsType() {
    if (getTags().contains(WeaponTag.Archery)) {
      return WeaponStatsType.Bow;
    }
    if (getTags().contains(WeaponTag.Flame)) {
      return WeaponStatsType.Flame;
    }
    if (getTags().contains(WeaponTag.Thrown)) {
      return WeaponStatsType.Thrown;
    }
    return WeaponStatsType.Melee;
  }

  @Override
  public int getDamage() {
    int damage = delegate.getDamage();
    StatModifier equipment = new EquipmentDamageModifier(modifiers.createModifiers(), getWeaponStatsType());
    return getModifiedValue(new DamageModification(equipment), damage);
  }

  @Override
  public TraitType getDamageTraitType() {
    return delegate.getDamageTraitType();
  }

  @Override
  public HealthType getDamageType() {
    return delegate.getDamageType();
  }

  @Override
  public Integer getDefence() {
    Integer defence = delegate.getDefence();
    StatModifier equipment = new EquipmentDefenceModifier(modifiers.createModifiers());
    return getModifiedValue(new DefenseModification(equipment), defence);
  }

  @Override
  public int getMobilityPenalty() {
    return delegate.getMobilityPenalty();
  }

  @Override
  public Collection<Identifier> getTags() {
    return delegate.getTags();
  }

  @Override
  public TraitType getTraitType() {
    return delegate.getTraitType();
  }

  @Override
  public boolean inflictsNoDamage() {
    return delegate.inflictsNoDamage();
  }

  @Override
  public Identifier getName() {
    return delegate.getName();
  }

  @Override
  public boolean isRangedCombat() {
    return delegate.isRangedCombat();
  }

  @Override
  public ItemStatsSet getViews() {
    return ItemStatsSet.withSingleStat(this);
  }

  @Override
  public int getOverwhelmingValue() {
    return delegate.getOverwhelmingValue();
  }

  @Override
  public String getId() {
    return delegate.getId();
  }

  @Override
  public boolean representsItemForUseInCombat() {
    return delegate.representsItemForUseInCombat();
  }

  private Integer getModifiedValue(StatsModification modification, Integer unmodifiedValue) {
    if (unmodifiedValue == null) {
      return null;
    }
    return modification.getModifiedValue(unmodifiedValue);
  }
}