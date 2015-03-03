package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;

public class SingleSpecialty {

  private final Hero hero;
  private final SpecialtyType type;
  
  public SingleSpecialty(Hero hero, TraitType type) {
    this.hero = hero;
    this.type = new SpecialtyTypeImpl(type, null);
  }
  
  public String toString() {
    return type.toString();
  }
  
  public int getValue() {
    return TraitModelFetcher.fetch(hero).getTrait(type.getTraitType()).getCurrentValue();
  }
}
