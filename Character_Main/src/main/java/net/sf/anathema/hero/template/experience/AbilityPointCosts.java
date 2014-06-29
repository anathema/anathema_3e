package net.sf.anathema.hero.template.experience;

public interface AbilityPointCosts {

  CurrentRatingCosts getAbilityCosts(boolean favored);

  int getMaximumFreeAbilityRank();
}