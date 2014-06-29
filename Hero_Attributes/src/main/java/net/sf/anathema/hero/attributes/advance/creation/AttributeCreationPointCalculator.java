package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.attributes.advance.CreationPointSumComparator;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.template.AttributePointsTemplate;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.template.points.IAttributeCreationPoints;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;

import java.util.*;
import java.util.function.Predicate;
import java.util.jar.Attributes;
import java.util.stream.Collectors;

import static net.sf.anathema.hero.template.points.AttributeGroupPriority.*;

public class AttributeCreationPointCalculator implements AttributeGroupPoints, HeroBonusPointCalculator {

  private final AttributeModel attributes;
  private final IAttributeCreationPoints creationPoints;
  private final AttributePointsTemplate template;
  private final Map<AttributeGroupPriority, Integer> incrementCount = new HashMap<>();

  public AttributeCreationPointCalculator(AttributeModel attributes, IAttributeCreationPoints creationPoints, AttributePointsTemplate template) {
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
    List<TraitGroup> attributeGroups = new ArrayList<>(Arrays.asList(attributes.getTraitGroups()));

    TraitGroup primaryGroup = findOptimalCandidate(attributeGroups, getFreebieCount(Primary));
    incrementCount.put(Primary, getIncrementCount(primaryGroup));
    attributeGroups.remove(primaryGroup);

    TraitGroup secondaryGroup = findOptimalCandidate(attributeGroups, getFreebieCount(Secondary));
    incrementCount.put(Secondary, getIncrementCount(secondaryGroup));
    attributeGroups.remove(secondaryGroup);

    incrementCount.put(Tertiary, getIncrementCount(attributeGroups.get(0)));
  }

  private TraitGroup findOptimalCandidate(List<TraitGroup> attributeGroups, int freebieCount) {
    attributeGroups.sort(new CreationPointSumComparator());
    List<TraitGroup> highValueGroups = attributeGroups.stream().filter(traitGroup -> getIncrementCount(traitGroup) >= freebieCount).collect(Collectors.toList());
    if (highValueGroups.isEmpty()) {
      return attributeGroups.get(attributeGroups.size() - 1);
    }
    highValueGroups.sort(new CreationPointSumComparator());
    return highValueGroups.get(0);
  }

  private Integer getIncrementCount(TraitGroup group) {
    int count = 0;
    for (Trait trait : group.getGroupTraits()) {
      count += trait.getCreationValue() - template.standard.calculationBase;
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
        return template.bonusPoints.primary;
      case Secondary:
        return template.bonusPoints.secondary;
      case Tertiary:
        return template.bonusPoints.tertiary;
    }
    throw new IllegalArgumentException("Unknown attribute group priority " + priority);
  }
}
