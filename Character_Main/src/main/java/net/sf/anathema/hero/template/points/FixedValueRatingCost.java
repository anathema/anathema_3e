package net.sf.anathema.hero.template.points;

import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.io.Serializable;

public class FixedValueRatingCost extends ReflectionEqualsObject implements CurrentRatingCost, Serializable {

  private final int value;

  public FixedValueRatingCost(int value) {
    this.value = value;
  }

  @Override
  public int getRatingCosts(int currentRating) {
    return value;
  }
}