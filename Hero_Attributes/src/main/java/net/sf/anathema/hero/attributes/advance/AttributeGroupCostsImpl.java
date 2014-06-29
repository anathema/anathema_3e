package net.sf.anathema.hero.attributes.advance;

import net.sf.anathema.hero.attributes.advance.creation.AttributeGroupCosts;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.template.points.IAttributeCreationPoints;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.hero.template.points.AttributeGroupPriority.*;

public class AttributeGroupCostsImpl implements AttributeGroupCosts, HeroBonusPointCalculator {

  private final AttributeModel attributes;
  private final IAttributeCreationPoints creationPoints;
  private final AttributeBonusPointTemplate template;
  private final Map<AttributeGroupPriority, Integer> incrementCount = new HashMap<>();

  public AttributeGroupCostsImpl(AttributeModel attributes, IAttributeCreationPoints creationPoints, AttributeBonusPointTemplate template) {
    this.attributes = attributes;
    this.creationPoints = creationPoints;
    this.template = template;
  }

  @Override
  public int getBonusPointsSpent(AttributeGroupPriority priority) {
    int pointsBought = Math.max(0, getIncrementCount(priority) - getFreebieCount(priority));
    return pointsBought * getBonusPointCostPerDot(priority);
  }

  @Override
  public Integer getFreebiesSpent(AttributeGroupPriority priority) {
    return Math.min(getFreebieCount(priority), getIncrementCount(priority));
  }

  @Override
  public int getFreebieCount(AttributeGroupPriority priority) {
    return creationPoints.getCount(priority);
  }

  private int getIncrementCount(AttributeGroupPriority priority) {
    return incrementCount.get(priority);
  }

  @Override
  public void recalculate() {
    incrementCount.clear();
    TraitGroup[] attributeGroups = attributes.getTraitGroups();
    Arrays.sort(attributeGroups, new CreationPointSumComparator());
    incrementCount.put(Tertiary, getIncrementCount(attributeGroups[0]));
    incrementCount.put(Secondary, getIncrementCount(attributeGroups[1]));
    incrementCount.put(Primary, getIncrementCount(attributeGroups[2]));
  }

  private Integer getIncrementCount(TraitGroup group) {
    int count = 0;
    for (Trait trait : group.getGroupTraits()) {
      count += trait.getCreationValue() - trait.getMinimalValue();
    }
    return count;
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
    switch (priority) {
      case Primary:
        return template.primary;
      case Secondary:
        return template.secondary;
      case Tertiary:
        return template.tertiary;
    }
    throw new IllegalArgumentException("Unknown attribute group priority " + priority);
  }
}
