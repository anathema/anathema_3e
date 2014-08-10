package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.List;

public interface SpecialtiesModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Specialties");
  
  List<Specialty> getAllSpecialties();
  
  List<Specialty> getAllSpecialtiesOfType(TraitType type);
  
  boolean removeSpecialty(Specialty specialty);

  List<TraitType> getAllEligibleParentTraits();

  void setCurrentTrait(TraitType newValue);

  void setCurrentSpecialtyName(String newSpecialtyName);

  void commitSelection();

  void clear();

  boolean isEntryComplete();

  boolean isExperienced();

  void addSelectionChangeListener(ChangeListener listener);
  
  void addSpecialtiesChangedListener(ISpecialtyListener listener);

  TraitType getCurrentTrait();

  String getCurrentName();
}