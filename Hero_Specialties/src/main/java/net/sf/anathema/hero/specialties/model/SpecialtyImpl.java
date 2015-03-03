package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.model.property.AbstractPossessedProperty;

public class SpecialtyImpl 
	extends AbstractPossessedProperty<SpecialtyType>
  implements Specialty {

  public SpecialtyImpl(SpecialtyType type, String description, boolean isLearnedAtCreation) {
    super(type, description, isLearnedAtCreation);
  }

  @Override
  public TraitType getBasicTraitType() {
    return getBaseOption().getTraitType();
  }
}