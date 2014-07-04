package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;

public interface Specialty extends Trait {

  String getName();

  TraitType getBasicTraitType();
}