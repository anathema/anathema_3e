package net.sf.anathema.hero.equipment.model;

import java.util.Collection;

import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.equipment.stats.ItemStatsSet;
import net.sf.anathema.equipment.stats.impl.AbstractStats;
import net.sf.anathema.equipment.stats.impl.Proxy;
import net.sf.anathema.equipment.stats.modification.StatsModification;
import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.identifier.Identifier;

import com.google.common.base.Objects;

public class ProxyWeaponStats extends AbstractStats implements IWeaponStats, Proxy<IWeaponStats> {

  private final IWeaponStats delegate;

  public ProxyWeaponStats(IWeaponStats stats) {
    this.delegate = stats;
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
    return delegate.getAccuracy();
  }

  @Override
  public int getDamage() {
    return delegate.getDamage();
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
    return delegate.getDefence();
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
}