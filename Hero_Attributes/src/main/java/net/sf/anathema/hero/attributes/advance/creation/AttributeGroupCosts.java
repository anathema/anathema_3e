package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.template.points.AttributeGroupPriority;

public interface AttributeGroupCosts {

  int getBonusPointsSpent(AttributeGroupPriority priority);

  Integer getFreebiesSpent(AttributeGroupPriority priority);

  int getFreebieCount(AttributeGroupPriority priority);
}
