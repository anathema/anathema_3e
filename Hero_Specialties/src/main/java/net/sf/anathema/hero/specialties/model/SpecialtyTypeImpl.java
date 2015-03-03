package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.model.property.AbstractOptionalPropertyOption;

public class SpecialtyTypeImpl extends AbstractOptionalPropertyOption implements SpecialtyType {

  private final TraitType type;
  
  public SpecialtyTypeImpl(TraitType type) {
    this.type = type;
  }

  @Override
  public TraitType getTraitType() {
    return type;
  }

}
