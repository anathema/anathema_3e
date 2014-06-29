package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.advance.overview.model.AbstractSpendingModel;

public class AttributeBonusModel extends AbstractSpendingModel {
  private final AttributeGroupPriority priority;
  private AttributeGroupPoints groupCosts;

  public AttributeBonusModel(AttributeGroupPoints groupCosts, AttributeGroupPriority priority) {
    super("Attributes", priority.getId());
    this.groupCosts = groupCosts;
    this.priority = priority;
  }

  @Override
  public int getSpentBonusPoints() {
    return groupCosts.getBonusPointsSpent(priority);
  }

  @Override
  public Integer getValue() {
    return groupCosts.getFreebiesSpent(priority);
  }

  @Override
  public int getAllotment() {
    return groupCosts.getFreebieCount(priority);
  }
}
