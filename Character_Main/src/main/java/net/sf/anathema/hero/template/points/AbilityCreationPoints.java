package net.sf.anathema.hero.template.points;

import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class AbilityCreationPoints extends ReflectionCloneableObject<IAbilityCreationPoints> implements IAbilityCreationPoints {
  private final int defaultDotCount;
  private final int favoredDotCount;

  public AbilityCreationPoints(int favoredDotCount, int defaultDotCount) {
    this.favoredDotCount = favoredDotCount;
    this.defaultDotCount = defaultDotCount;
  }

  @Override
  public int getFavoredDotCount() {
    return favoredDotCount;
  }

  @Override
  public int getDefaultDotCount() {
    return defaultDotCount;
  }

  @Override
  public AbilityCreationPoints clone() {
    return (AbilityCreationPoints) super.clone();
  }
}