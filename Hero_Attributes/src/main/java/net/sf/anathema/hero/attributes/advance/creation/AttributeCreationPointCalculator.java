package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.traits.model.TraitGroup;
import net.sf.anathema.points.model.BonusPointCalculator;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.hero.template.points.AttributeGroupPriority.Primary;
import static net.sf.anathema.hero.template.points.AttributeGroupPriority.Secondary;
import static net.sf.anathema.hero.template.points.AttributeGroupPriority.Tertiary;

public class AttributeCreationPointCalculator implements AttributeGroupPoints, BonusPointCalculator {

  private final AttributeModel attributes;
  private AttributeCreationData creationData;
  private final Map<AttributeGroupPriority, Integer> incrementCount = new HashMap<>();

  public AttributeCreationPointCalculator(AttributeModel attributes, AttributeCreationData creationData) {
    this.attributes = attributes;
    this.creationData = creationData;
  }

  @Override
  public int getBonusPointsSpent(AttributeGroupPriority priority) {
    int dotsBought = Math.max(0, getIncrementCount(priority) - getFreebieCount(priority));
    int bonusPointsPerDot = creationData.getBonusPointCostPerDot(priority);
    return dotsBought * bonusPointsPerDot;
  }

  @Override
  public Integer getFreebiesSpent(AttributeGroupPriority priority) {
    return Math.min(getFreebieCount(priority), getIncrementCount(priority));
  }

  @Override
  public int getFreebieCount(AttributeGroupPriority priority) {
    return creationData.getFreebieCount(priority);
  }

  private int getIncrementCount(AttributeGroupPriority priority) {
    return incrementCount.get(priority);
  }

  @Override
  public void recalculate() {
    incrementCount.clear();
    AttributeGroupList attributeGroupList = new AttributeGroupList(attributes.getTraitGroups(), createIncrementCounter());
    rememberIncrementsForPriorityAndRemoveTheMatchingGroup(attributeGroupList, Primary);
    rememberIncrementsForPriorityAndRemoveTheMatchingGroup(attributeGroupList, Secondary);
    rememberIncrementsForPriorityAndRemoveTheMatchingGroup(attributeGroupList, Tertiary);
  }

  private void rememberIncrementsForPriorityAndRemoveTheMatchingGroup(AttributeGroupList attributeGroupList, AttributeGroupPriority priority) {
    TraitGroup bestGroup = findGroupThatExceedsFreebieLimitTheLeast(attributeGroupList, getFreebieCount(priority));
    incrementCount.put(priority, createIncrementCounter().getIncrementCount(bestGroup));
    attributeGroupList.remove(bestGroup);
  }

  private IncrementCounter createIncrementCounter() {
    return new IncrementCounter(creationData);
  }

  private TraitGroup findGroupThatExceedsFreebieLimitTheLeast(AttributeGroupList attributeGroups, int freebieCount) {
    AttributeGroupList exceedingGroups = attributeGroups.getGroupsThatSpendXOrMorePoints(freebieCount);
    if (exceedingGroups.hasElements()) {
      return exceedingGroups.findGroupWithMinimalIncrements();
    }
    return attributeGroups.findGroupWithMaximalIncrements();
  }

  @Override
  public int getBonusPointCost() {
    return getBonusPointsSpent(Primary) + getBonusPointsSpent(Secondary) + getBonusPointsSpent(Tertiary);
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }
}
