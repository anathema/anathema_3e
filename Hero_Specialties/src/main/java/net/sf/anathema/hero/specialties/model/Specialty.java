package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.model.property.PossessedOptionalProperty;

public interface Specialty extends PossessedOptionalProperty<SpecialtyType> {
  TraitType getBasicTraitType();
}