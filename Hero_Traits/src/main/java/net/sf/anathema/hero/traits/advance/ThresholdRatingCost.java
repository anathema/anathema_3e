package net.sf.anathema.hero.traits.advance;

import net.sf.anathema.library.lang.ReflectionEqualsObject;

import java.io.Serializable;

public class ThresholdRatingCost extends ReflectionEqualsObject implements CurrentRatingCost, Serializable {

  private final int lowCost;
  private final int highCost;
  private final int threshold;

  public ThresholdRatingCost(int lowCost, int highCost, int threshold) {
    this.lowCost = lowCost;
    this.highCost = highCost;
    this.threshold = threshold;
  }

  @Override
  public int getRatingCosts(int currentRating) {
    return currentRating < threshold ? lowCost : highCost;
  }
}