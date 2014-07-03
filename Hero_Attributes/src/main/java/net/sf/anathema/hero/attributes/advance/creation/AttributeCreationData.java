package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.attributes.advance.AttributeGroupPriority;
import net.sf.anathema.hero.attributes.template.AttributeGroupPointsTemplate;
import net.sf.anathema.hero.attributes.template.AttributePointsTemplate;
import net.sf.anathema.hero.traits.advance.TraitListCreationData;
import net.sf.anathema.hero.traits.model.TraitType;

public class AttributeCreationData implements TraitListCreationData {

  private AttributePointsTemplate template;

  public AttributeCreationData(AttributePointsTemplate template) {
    this.template = template;
  }

  public int getFreebieCount(AttributeGroupPriority priority) {
    return getPoints(priority, template.freebies);
  }

  public int getBonusPointCostPerDot(AttributeGroupPriority priority) { return getPoints(priority, template.bonusPoints); }

  private int getPoints(AttributeGroupPriority priority, AttributeGroupPointsTemplate groupPointsTemplate) {
    switch (priority) {
      case Primary:
        return groupPointsTemplate.primary;
      case Secondary:
        return groupPointsTemplate.secondary;
      case Tertiary:
        return groupPointsTemplate.tertiary;
    }
    throw new IllegalArgumentException("Unknown attribute group priority " + priority);
  }

  @Override
  public int getCalculationBase(TraitType type) {
    return  template.standard.calculationBase;
  }
}
