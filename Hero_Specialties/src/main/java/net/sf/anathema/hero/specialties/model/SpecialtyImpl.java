package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitType;

public class SpecialtyImpl implements Specialty {

  private final String specialtyName;
  private final TraitType type;
  private final boolean isLearnedAtCreation;

  public SpecialtyImpl(Hero hero, TraitType type, String specialtyName, boolean isLearnedAtCreation) {
    this.type = type;
    this.specialtyName = specialtyName;
    this.isLearnedAtCreation = isLearnedAtCreation;
  }

  @Override
  public String getName() {
    return specialtyName;
  }

  @Override
  public TraitType getBasicTraitType() {
    return type;
  }
  
  @Override
  public boolean isLearnedAtCreation() {
  	return isLearnedAtCreation;
  }
  
  @Override
  public int getCurrentValue() {
  	return 1;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SpecialtyImpl)) {
      return false;
    }
    SpecialtyImpl other = (SpecialtyImpl) obj;
    return super.equals(obj) && other.getName().equals(getName()) && other.getBasicTraitType() == getBasicTraitType();
  }

  @Override
  public int hashCode() {
    return getName().hashCode() + getBasicTraitType().hashCode();
  }
}