package net.sf.anathema.equipment.stats.impl;

import java.util.Collection;

import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.library.identifier.Identifier;

import com.google.common.base.Objects;

public class ProxyArmourStats extends AbstractStats implements IArmourStats, Proxy<IArmourStats> {

  private final IArmourStats delegate;

  public ProxyArmourStats(IArmourStats stats) {
    this.delegate = stats;
  }

  @Override
  public IArmourStats getUnderlying() {
    return this.delegate;
  }

  @Override
  public Integer getHardness() {
    return delegate.getHardness();
  }

  @Override
  public Integer getMobilityPenalty() {
    return delegate.getMobilityPenalty();
  }

  @Override
  public Integer getSoak() {
    return delegate.getSoak();
  }

  @Override
  public Collection<Identifier> getTags() {
    return delegate.getTags();
  }

  @Override
  public Identifier getName() {
    return delegate.getName();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ProxyArmourStats)) {
      return false;
    }
    ProxyArmourStats other = (ProxyArmourStats) obj;
    return Objects.equal(delegate, other.delegate);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  @Override
  public String getId() {
    return getName().getId();
  }

  @Override
  public boolean representsItemForUseInCombat() {
    return delegate.representsItemForUseInCombat();
  }
}
