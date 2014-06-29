package net.sf.anathema.character.framework.xml.experience;

import net.sf.anathema.hero.template.experience.CurrentRatingCost;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;
import net.sf.anathema.hero.template.points.FixedValueRatingCost;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericExperiencePointCosts extends ReflectionCloneableObject<GenericExperiencePointCosts> implements IExperiencePointCosts {

  private CurrentRatingCost generalAttributeCost = new FixedValueRatingCost(0);
  private CurrentRatingCost favoredAttributeCost = new FixedValueRatingCost(0);
  private CurrentRatingCost generalAbilityCost = new FixedValueRatingCost(0);
  private CurrentRatingCost favoredAbilityCost = new FixedValueRatingCost(0);
  private int specialtyCost = 0;

  @Override
  public CurrentRatingCost getAbilityCosts(boolean favored) {
    return favored ? favoredAbilityCost : generalAbilityCost;
  }

  @Override
  public CurrentRatingCost getAttributeCosts(boolean favored) {
    return favored ? favoredAttributeCost : generalAttributeCost;
  }

  @Override
  public int getSpecialtyCosts(boolean favored) {
    return specialtyCost;
  }

  public void setGeneralAttributeCosts(CurrentRatingCost generalAttributeCost) {
    this.generalAttributeCost = generalAttributeCost;
  }

  public void setFavoredAttributeCosts(CurrentRatingCost favoredAttributeCost) {
    this.favoredAttributeCost = favoredAttributeCost;
  }

  public void setGeneralAbilityCosts(CurrentRatingCost generalAbilityCost) {
    this.generalAbilityCost = generalAbilityCost;
  }

  public void setFavoredAbilityCosts(CurrentRatingCost favoredAbilityCost) {
    this.favoredAbilityCost = favoredAbilityCost;
  }

  public void setSpecialtyCosts(int specialtyCost) {
    this.specialtyCost = specialtyCost;
  }
}