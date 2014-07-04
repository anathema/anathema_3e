package net.sf.anathema.hero.equipment.model;

import com.google.common.base.Objects;
import net.sf.anathema.character.equipment.character.model.stats.AbstractStats;
import net.sf.anathema.character.equipment.character.model.stats.modification.AccuracyModification;
import net.sf.anathema.character.equipment.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.character.model.stats.modification.DamageModification;
import net.sf.anathema.character.equipment.character.model.stats.modification.DefenseModification;
import net.sf.anathema.character.equipment.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.StatsModification;
import net.sf.anathema.character.equipment.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.equipment.character.model.stats.modification.equipment.EquipmentAccuracyModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.equipment.EquipmentDamageModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.equipment.EquipmentDefenceModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.material.MaterialAccuracyModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.material.MaterialDamageModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.material.MaterialDefenceModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.modifier.AttunementModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.modifier.BestModifier;
import net.sf.anathema.character.equipment.creation.model.WeaponTag;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.framework.library.Proxy;
import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.identifier.Identifier;

import java.util.Collection;

public class ProxyWeaponStats extends AbstractStats implements IWeaponStats, Proxy<IWeaponStats> {

  private final IWeaponStats delegate;
  private final BaseMaterial material;
  private final ModifierFactory modifiers;

  public ProxyWeaponStats(IWeaponStats stats, BaseMaterial material, ModifierFactory modifiers) {
    this.delegate = stats;
    this.material = material;
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
    return Objects.equal(delegate, other.delegate) && Objects.equal(material, other.material);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  @Override
  public int getAccuracy() {
    int accuracy = delegate.getAccuracy();
    WeaponStatsType type = getWeaponStatsType();
    StatModifier material = createAttunementModifier(new MaterialAccuracyModifier(this.material, type));
    StatModifier equipment = new EquipmentAccuracyModifier(modifiers.createModifiers(), type);
    StatModifier modifier = new BestModifier(material, equipment);
    return getModifiedValue(new AccuracyModification(modifier), accuracy);
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
    StatModifier material = createAttunementModifier(new MaterialDamageModifier(this.material, getWeaponStatsType()));
    StatModifier equipment = new EquipmentDamageModifier(modifiers.createModifiers(), getWeaponStatsType());
    StatModifier modifier = new BestModifier(material, equipment);
    return getModifiedValue(new DamageModification(modifier), damage);
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
    StatModifier material = createAttunementModifier(new MaterialDefenceModifier(this.material));
    StatModifier equipment = new EquipmentDefenceModifier(modifiers.createModifiers());
    StatModifier modifier = new BestModifier(material, equipment);
    return getModifiedValue(new DefenseModification(modifier), defence);
  }

  @Override
  public int getMobilityPenalty() {
    return delegate.getMobilityPenalty();
  }

  @Override
  public Collection<Identifier> getTags() {
    return delegate.getTags();
  }

  private AttunementModifier createAttunementModifier(StatModifier modifier) {
    return new AttunementModifier(modifier);
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
  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[]{this};
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