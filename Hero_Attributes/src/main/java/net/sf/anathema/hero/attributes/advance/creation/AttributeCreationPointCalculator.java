package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.template.AttributeGroupPointsTemplate;
import net.sf.anathema.hero.attributes.template.AttributePointsTemplate;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.traits.model.TraitGroup;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.hero.template.points.AttributeGroupPriority.*;

public class AttributeCreationPointCalculator implements AttributeGroupPoints, HeroBonusPointCalculator {

  private final AttributeModel attributes;
  private final AttributePointsTemplate template;
  private final Map<AttributeGroupPriority, Integer> incrementCount = new HashMap<>();

  public AttributeCreationPointCalculator(AttributeModel attributes, AttributePointsTemplate template) {
    this.attributes = attributes;
    this.template = template;
  }

  @Override
  public int getBonusPointsSpent(AttributeGroupPriority priority) {
    int dotsBought = Math.max(0, getIncrementCount(priority) - getFreebieCount(priority));
    int bonusPointsPerDot = getBonusPointCostPerDot(priority);
    return dotsBought * bonusPointsPerDot;
  }

  @Override
  public Integer getFreebiesSpent(AttributeGroupPriority priority) {
    return Math.min(getFreebieCount(priority), getIncrementCount(priority));
  }

  @Override
  public int getFreebieCount(AttributeGroupPriority priority) {
    return getPoints(priority, template.freebies);
  }

  private int getIncrementCount(AttributeGroupPriority priority) {
    return incrementCount.get(priority);
  }

  @Override
  public void recalculate() {
    incrementCount.clear();
    AttributeGroupList attributeGroupList = new AttributeGroupList(attributes.getTraitGroups(), new IncrementCounter(template));
    rememberIncrementsForPriorityAndRemoveTheMatchingGroup(attributeGroupList, Primary);
    rememberIncrementsForPriorityAndRemoveTheMatchingGroup(attributeGroupList, Secondary);
    rememberIncrementsForPriorityAndRemoveTheMatchingGroup(attributeGroupList, Tertiary);
  }

  private void rememberIncrementsForPriorityAndRemoveTheMatchingGroup(AttributeGroupList attributeGroupList, AttributeGroupPriority priority) {
    TraitGroup bestGroup = findGroupThatExceedsFreebieLimitTheLeast(attributeGroupList, getFreebieCount(priority));
    incrementCount.put(priority, new IncrementCounter(template).getIncrementCount(bestGroup));
    attributeGroupList.remove(bestGroup);
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

  public int getBonusPointCostPerDot(AttributeGroupPriority priority) {
    return getPoints(priority, template.bonusPoints);
  }

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
}
