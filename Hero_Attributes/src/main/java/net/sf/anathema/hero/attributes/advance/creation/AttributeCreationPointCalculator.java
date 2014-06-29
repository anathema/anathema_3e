package net.sf.anathema.hero.attributes.advance.creation;

import com.google.common.collect.Iterables;
import net.sf.anathema.hero.attributes.advance.CreationPointSumComparator;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.template.AttributeGroupPointsTemplate;
import net.sf.anathema.hero.attributes.template.AttributePointsTemplate;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;

import java.util.*;

import static java.util.stream.Collectors.toList;
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
    int pointsBought = Math.max(0, getIncrementCount(priority) - getFreebieCount(priority));
    return pointsBought * getBonusPointCostPerDot(priority);
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
    List<TraitGroup> attributeGroups = new ArrayList<>(Arrays.asList(attributes.getTraitGroups()));
    rememberIncrementsForPriorityAndRemoveTheMatchingGroup(attributeGroups, Primary);
    rememberIncrementsForPriorityAndRemoveTheMatchingGroup(attributeGroups, Secondary);
    rememberIncrementsForPriorityAndRemoveTheMatchingGroup(attributeGroups, Tertiary);
  }

  private void rememberIncrementsForPriorityAndRemoveTheMatchingGroup(List<TraitGroup> attributeGroups, AttributeGroupPriority priority) {
    TraitGroup bestGroup = findGroupThatExceedsFreebieLimitTheLeast(attributeGroups, getFreebieCount(priority));
    incrementCount.put(priority, getIncrementCount(bestGroup));
    attributeGroups.remove(bestGroup);
  }

  private TraitGroup findGroupThatExceedsFreebieLimitTheLeast(List<TraitGroup> groups, int freebieCount) {
    List<TraitGroup> exceedingGroups = groups.stream().filter(traitGroup -> getIncrementCount(traitGroup) >= freebieCount).collect(toList());
    if (!exceedingGroups.isEmpty()) {
      return findGroupWithMinimalIncrements(exceedingGroups);
    }
    return findGroupWithMaximalIncrements(groups);
  }

  private TraitGroup findGroupWithMinimalIncrements(List<TraitGroup> groups) {
    groups.sort(new CreationPointSumComparator());
    return groups.get(0);
  }

  private TraitGroup findGroupWithMaximalIncrements(List<TraitGroup> groups) {
    groups.sort(new CreationPointSumComparator());
    return Iterables.getLast(groups);
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
