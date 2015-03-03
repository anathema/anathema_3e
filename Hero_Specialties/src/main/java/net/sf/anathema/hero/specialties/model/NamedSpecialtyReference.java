package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.sheet.content.ValuedTraitReference;

public class NamedSpecialtyReference implements ValuedTraitReference {

  private final Specialty specialty;
  
  public NamedSpecialtyReference(Specialty specialty) {
    this.specialty = specialty;
  }
  
  @Override
  public TraitType getTraitType() {
    return specialty.getBasicTraitType();
  }

  @Override
  public String getName() {
    return specialty.getDescription();
  }

  @Override
  public int getValue() {
    return 1;
  }

}
