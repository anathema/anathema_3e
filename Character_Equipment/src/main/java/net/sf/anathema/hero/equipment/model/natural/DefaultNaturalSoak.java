package net.sf.anathema.hero.equipment.model.natural;

import net.sf.anathema.character.equipment.character.model.stats.AbstractCombatStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.Collection;
import java.util.Collections;

public class DefaultNaturalSoak extends AbstractCombatStats implements IArmourStats, NaturalSoak {

  private final ValuedTraitType stamina;

  public DefaultNaturalSoak(ValuedTraitType stamina) {
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