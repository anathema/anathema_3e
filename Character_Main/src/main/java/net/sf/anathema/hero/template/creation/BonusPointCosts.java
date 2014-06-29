package net.sf.anathema.hero.template.creation;

import net.sf.anathema.hero.template.experience.CurrentRatingCosts;

public interface BonusPointCosts {

  int getWillpowerCosts();

  CurrentRatingCosts getEssenceCost();
}