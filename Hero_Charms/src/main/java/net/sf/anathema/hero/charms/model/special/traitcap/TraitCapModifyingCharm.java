package net.sf.anathema.hero.charms.model.special.traitcap;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;
import net.sf.anathema.hero.traits.model.TraitType;

public class TraitCapModifyingCharm implements ITraitCapModifyingCharm {
  private final CharmName charmId;
  private final TraitType traitType;
  private final int modifier;

  public TraitCapModifyingCharm(CharmName charmId, TraitType trait, int modifier) {
    this.charmId = charmId;
    this.traitType = trait;
    this.modifier = modifier;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitTraitCapModifyingCharm(this);
  }

  @Override
  public CharmName getCharmName() {
    return charmId;
  }

  @Override
  public TraitType getTraitType() {
    return traitType;
  }

  @Override
  public int getModifier() {
    return modifier;
  }

  public String toString() {
    return "[" + getCharmName() + ";mod " + traitType.getId() + "]";
  }
}