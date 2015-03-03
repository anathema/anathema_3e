package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.hero.specialties.model.Specialty;

import java.util.Collection;

public class SpecialtyCalculator {

  private final SpecialtyCreationData data;

  public SpecialtyCalculator(SpecialtyCreationData data) {
    this.data = data;
  }

  public int getFreebiePointsSpent(Collection<Specialty> specialties) {
    return Math.min(data.getFreebiePoints(), specialties.size());
  }

  public int getSpecialtyCosts(Collection<Specialty> specialties) {
    return data.getBonusPointCostPerDot() * Math.max(0, specialties.size() - getFreebiePointsSpent(specialties));
  }
}