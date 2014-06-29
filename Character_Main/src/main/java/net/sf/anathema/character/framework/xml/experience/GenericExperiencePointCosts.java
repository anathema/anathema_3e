package net.sf.anathema.character.framework.xml.experience;

import net.sf.anathema.hero.template.experience.CurrentRatingCost;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;
import net.sf.anathema.hero.template.points.FixedValueRatingCost;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericExperiencePointCosts extends ReflectionCloneableObject<GenericExperiencePointCosts> implements IExperiencePointCosts {

  private int specialtyCost = 0;

  @Override
  public int getSpecialtyCosts(boolean favored) {
    return specialtyCost;
  }

  public void setSpecialtyCosts(int specialtyCost) {
    this.specialtyCost = specialtyCost;
  }
}