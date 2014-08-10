package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.model.TraitType;

public interface Specialty {

  String getName();

  TraitType getBasicTraitType();
  
  boolean isLearnedAtCreation();
  
  int getCurrentValue();
}