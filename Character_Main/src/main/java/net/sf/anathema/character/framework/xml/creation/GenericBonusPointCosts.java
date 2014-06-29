package net.sf.anathema.character.framework.xml.creation;

import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.lang.clone.ICloneable;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class GenericBonusPointCosts extends ReflectionEqualsObject implements BonusPointCosts, ICloneable<GenericBonusPointCosts>, Serializable {

  private int willpowerCost = 0;

  @Override
  public int getWillpowerCosts() {
    return willpowerCost;
  }

  public void setWillpowerCosts(int willpowerCost) {
    this.willpowerCost = willpowerCost;
  }

  @Override
  public GenericBonusPointCosts clone() {
    return SerializationUtils.clone(this);
  }
}