package net.sf.anathema.hero.traits.advance;

import net.sf.anathema.library.lang.ReflectionEqualsObject;

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