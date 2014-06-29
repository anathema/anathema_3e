package net.sf.anathema.hero.template.creation;

import net.sf.anathema.hero.template.experience.AbilityPointCosts;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;

public interface BonusPointCosts extends AbilityPointCosts {

  int getWillpowerCosts();

  CurrentRatingCosts getEssenceCost();
}