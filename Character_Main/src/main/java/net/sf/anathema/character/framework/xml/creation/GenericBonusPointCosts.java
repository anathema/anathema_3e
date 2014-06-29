package net.sf.anathema.character.framework.xml.creation;

import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.template.points.FixedValueRatingCosts;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.lang.clone.ICloneable;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class GenericBonusPointCosts extends ReflectionEqualsObject implements BonusPointCosts, ICloneable<GenericBonusPointCosts>, Serializable {

  private CurrentRatingCosts essenceCost = new FixedValueRatingCosts(0);
  private int willpowerCost = 0;

  @Override
  public int getWillpowerCosts() {
    return willpowerCost;
  }

  @Override
  public CurrentRatingCosts getEssenceCost() {
    return essenceCost;
  }

  public void setWillpowerCosts(int willpowerCost) {
    this.willpowerCost = willpowerCost;
  }

  public void setEssenceCosts(CurrentRatingCosts costs) {
    this.essenceCost = costs;
  }

  @Override
  public GenericBonusPointCosts clone() {
    return SerializationUtils.clone(this);
  }
}