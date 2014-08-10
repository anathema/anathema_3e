package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;

import java.util.Collection;

public class HighestSpecialty {

  private String name;

  public HighestSpecialty(Hero hero, AbilityType type) {
    for (Specialty t : SpecialtiesModelFetcher.fetch(hero).getAllSpecialtiesOfType(type)) {
      name = t.getName();
    }
  }
  
  public int getValue() {
  	return name == null ? 0 : 1;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}