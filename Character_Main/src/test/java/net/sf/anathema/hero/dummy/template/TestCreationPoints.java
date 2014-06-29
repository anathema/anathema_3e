package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.hero.template.points.AbilityCreationPoints;
import net.sf.anathema.hero.template.points.AbstractCreationPoints;
import net.sf.anathema.hero.template.points.IAbilityCreationPoints;

public class TestCreationPoints extends AbstractCreationPoints {

  @Override
  public int getBonusPointCount() {
    return 15;
  }

  @Override
  public IAbilityCreationPoints getAbilityCreationPoints() {
    return new AbilityCreationPoints(10, 15);
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