package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.hero.traits.model.Trait;

public class GenericSpecialty implements IGenericSpecialty {

  private final Trait trait;

  public GenericSpecialty(Trait trait) {
    this.trait = trait;
  }

  @Override
  public Trait getBasicTrait() {
    return trait;
  }
}