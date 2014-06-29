package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.hero.template.creation.ICreationPoints;
import net.sf.anathema.hero.template.points.IAbilityCreationPoints;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummyCreationPoints implements ICreationPoints {

  public IAbilityCreationPoints abilityCreationPoints = new DummyAbilityCreationPoints();
  public int favoredCreationCharmCount = 0;
  public int defaultCreationCharmCount = 0;

  @Override
  public int getBonusPointCount() {
    throw new NotYetImplementedException();
  }

  @Override
  public IAbilityCreationPoints getAbilityCreationPoints() {
    return abilityCreationPoints;
  }

  @Override
  public int getFavoredCreationMagicCount() {
    return favoredCreationCharmCount;
  }

  @Override
  public int getDefaultCreationMagicCount() {
    return defaultCreationCharmCount;
  }
}
