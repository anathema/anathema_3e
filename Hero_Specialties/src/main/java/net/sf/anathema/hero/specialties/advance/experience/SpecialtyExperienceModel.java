package net.sf.anathema.hero.specialties.advance.experience;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.points.display.overview.model.AbstractIntegerValueModel;

public class SpecialtyExperienceModel extends AbstractIntegerValueModel {

  private Hero hero;
  private SpecialtyExperienceData experienceData;

  public SpecialtyExperienceModel(Hero hero, SpecialtyExperienceData experienceData) {
    super("Experience", "Specialties");
    this.hero = hero;
    this.experienceData = experienceData;
  }

  @Override
  public Integer getValue() {
    return getSpecialtyCosts();
  }

  private int getSpecialtyCosts() {
    int experienceCosts = 0;
    for (Specialty specialty : SpecialtiesModelFetcher.fetch(hero).getAllSpecialties()) {
      experienceCosts += !specialty.isLearnedAtCreation() ? getCostPerSpecialtyDot() : 0;
    }
    return experienceCosts;
  }

  private int getCostPerSpecialtyDot() {
    return experienceData.getSpecialtyExperienceCost(false);
  }
}