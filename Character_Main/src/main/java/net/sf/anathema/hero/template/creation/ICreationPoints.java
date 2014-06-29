package net.sf.anathema.hero.template.creation;

import net.sf.anathema.hero.template.points.IAbilityCreationPoints;

public interface ICreationPoints {

  int getBonusPointCount();

  IAbilityCreationPoints getAbilityCreationPoints();

  int getFavoredCreationMagicCount();

  int getDefaultCreationMagicCount();
}