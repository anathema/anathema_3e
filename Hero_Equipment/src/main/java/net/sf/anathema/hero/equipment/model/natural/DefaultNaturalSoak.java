package net.sf.anathema.hero.equipment.model.natural;

import java.util.Collection;
import java.util.Collections;

import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.equipment.stats.impl.AbstractCombatStats;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class DefaultNaturalSoak extends AbstractCombatStats implements IArmourStats, NaturalSoak {

  private final Trait stamina;

  public DefaultNaturalSoak(Trait stamina) {
    this.stamina = stamina;
  }

  @Override
  public Integer getHardness() {
    return null;
  }

  @Override
  public Integer getMobilityPenalty() {
    return null;
  }

  @Override
  public Integer getSoak() {
    return stamina.getCurrentValue();
  }

  @Override
  public Collection<Identifier> getTags() {
    return Collections.emptyList();
  }

  @Override
  public Identifier getName() {
    return new SimpleIdentifier("NaturalSoak");
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}