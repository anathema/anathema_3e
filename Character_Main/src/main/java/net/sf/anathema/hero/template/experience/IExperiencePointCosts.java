package net.sf.anathema.hero.template.experience;

public interface IExperiencePointCosts {

  CurrentRatingCost getAbilityCosts(boolean favored);

  CurrentRatingCost getAttributeCosts(boolean favored);

  int getSpecialtyCosts(boolean favored);
}