package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.model.NullCategory;
import net.sf.anathema.library.model.property.OptionalPropertiesModel;

import java.util.List;

public interface SpecialtiesModel extends HeroModel,
	OptionalPropertiesModel<NullCategory, SpecialtyType, Specialty> {

  Identifier ID = new SimpleIdentifier("Specialties");
  
  List<Specialty> getAllSpecialtiesOfType(TraitType type);

  List<TraitType> getAllEligibleParentTraits();
}