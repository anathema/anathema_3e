package net.sf.anathema.hero.specialties.advance.creation;

import java.util.Collection;

import net.sf.anathema.hero.specialties.model.Specialty;

public class SpecialtyCalculator {

  private final int specialtyPoints;

  public SpecialtyCalculator(int specialtyPoints) {
    this.specialtyPoints = specialtyPoints;
  }

  public int getSpecialtyPointsSpent(Collection<Specialty> specialties) {
    return Math.min(specialtyPoints, specialties.size());
  }

  public int getSpecialtyCosts(Collection<Specialty> specialties) {
    return Math.max(0, specialties.size() - getSpecialtyPointsSpent(specialties));
  }
}