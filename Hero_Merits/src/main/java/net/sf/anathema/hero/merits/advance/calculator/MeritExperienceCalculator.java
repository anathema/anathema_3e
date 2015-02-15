package net.sf.anathema.hero.merits.advance.calculator;

import net.sf.anathema.hero.merits.advance.MeritExperienceData;
import net.sf.anathema.hero.merits.model.Merit;

public class MeritExperienceCalculator {
  private final MeritExperienceData experience;

  public MeritExperienceCalculator(MeritExperienceData experience) {
    this.experience = experience;
  }

  public int getMeritCosts(Merit merit) {
    int traitCosts = 0;
    int currentRating = merit.getCreationValue();
    while (currentRating < merit.getExperiencedValue()) {
      while (!merit.getBaseOption().isLegalValue(currentRating + 1)) {
        currentRating++;
      }
      traitCosts += experience.getCostCalculator().getRatingCosts(currentRating);
      currentRating++;
    }
    return traitCosts;
  }

}