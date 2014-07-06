package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface SpecialtiesModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Specialties");

  ISubTraitContainer getSpecialtiesContainer(TraitType traitType);

  Iterable<TraitType> getAllParentTraits();

  TraitType[] getAllEligibleParentTraits();

  void setCurrentTrait(TraitType newValue);

  void setCurrentSpecialtyName(String newSpecialtyName);

  void commitSelection();

  void clear();

  boolean isEntryComplete();

  boolean isExperienced();

  void addSelectionChangeListener(ChangeListener listener);

  TraitType getCurrentTrait();

  String getCurrentName();
}