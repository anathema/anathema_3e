package net.sf.anathema.hero.traits.advance;

import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.io.Serializable;

public class MultiplyRatingCost extends ReflectionEqualsObject implements CurrentRatingCost, Serializable {

  private final int factor;
  private final int initalCost;
  private final int summand;

  public MultiplyRatingCost(int factor) {
    this(factor, Integer.MIN_VALUE);
  }

  public MultiplyRatingCost(int factor, int initalCost) {
    this(factor, initalCost, 0);
  }

  public MultiplyRatingCost(int factor, int initalCost, int summand) {
    this.factor = factor;
    this.initalCost = initalCost;
    this.summand = summand;
  }

  @Override
  public int getRatingCosts(int currentRating) {
    if (currentRating > 0) {
      return currentRating * factor + summand;
    }
    if (initalCost <= 0) {
      throw new UnsupportedOperationException("Illegal learning");
    }
    return initalCost;
  }

  @Override
  public String toString() {
    return "MultiplyRatingCost " + factor + "/" + summand + "/" + initalCost;
  }
}