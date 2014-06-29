package net.sf.anathema.hero.template.experience;

public interface IExperiencePointCosts {

  CurrentRatingCost getAttributeCosts(boolean favored);

  int getSpecialtyCosts(boolean favored);
}