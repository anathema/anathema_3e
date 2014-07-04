package net.sf.anathema.character.equipment.dummy;

import net.sf.anathema.character.equipment.character.model.stats.AbstractCombatStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.library.identifier.Identifier;

public class DemoNaturalArmour extends AbstractCombatStats implements IArmourStats {

  private final int soak;
  private final Identifier name;

  public DemoNaturalArmour(Identifier identificate, int soak) {
    this.name = identificate;
    this.soak = soak;
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
    return soak;
  }

  @Override
  public Identifier[] getTags() {
    return new Identifier[0];
  }

  @Override
  public Identifier getName() {
    return name;
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}