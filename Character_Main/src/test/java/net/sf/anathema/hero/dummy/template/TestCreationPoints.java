package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.hero.template.points.AbstractCreationPoints;

public class TestCreationPoints extends AbstractCreationPoints {

  @Override
  public int getBonusPointCount() {
    return 15;
  }

  @Override
  public int getFavoredCreationMagicCount() {
    return 5;
  }

  @Override
  public int getDefaultCreationMagicCount() {
    return 5;
  }
}