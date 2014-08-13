package net.sf.anathema.hero.specialties.advance.experience;

import net.sf.anathema.hero.specialties.template.SpecialtyPointsTemplate;

public class SpecialtyExperienceData {

  private SpecialtyPointsTemplate template;

  public SpecialtyExperienceData(SpecialtyPointsTemplate template) {
    this.template = template;
  }

  public int getSpecialtyExperienceCost(boolean favored) {
    return template.experiencePointCost;
  }
}
