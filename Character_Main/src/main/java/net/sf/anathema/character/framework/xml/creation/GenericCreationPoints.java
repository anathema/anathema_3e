package net.sf.anathema.character.framework.xml.creation;

import com.google.common.base.Preconditions;
import net.sf.anathema.hero.template.creation.ICreationPoints;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericCreationPoints extends ReflectionCloneableObject<GenericCreationPoints> implements ICreationPoints {

  private int bonusPointCount = 0;
  private int defaultCreationCharmCount = 0;
  private int favoredCreationCharmCount = 0;

  @Override
  public int getBonusPointCount() {
    return bonusPointCount;
  }

  @Override
  public int getDefaultCreationMagicCount() {
    return defaultCreationCharmCount;
  }

  @Override
  public int getFavoredCreationMagicCount() {
    return favoredCreationCharmCount;
  }

  public void setBonusPointCount(int bonusPointCount) {
    Preconditions.checkArgument(bonusPointCount >= 0, "Bonus point count must be positive.");
    this.bonusPointCount = bonusPointCount;
  }

  public void setGeneralCreationCharmCount(int charmCount) {
    Preconditions.checkArgument(charmCount >= 0, "Default charm count must be positive.");
    this.defaultCreationCharmCount = charmCount;
  }

  public void setFavoredCreationCharmCount(int charmCount) {
    Preconditions.checkArgument(charmCount >= 0, "Favored charm count must be positive.");
    this.favoredCreationCharmCount = charmCount;
  }
}