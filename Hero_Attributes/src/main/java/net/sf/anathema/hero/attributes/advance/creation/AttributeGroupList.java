package net.sf.anathema.hero.attributes.advance.creation;

import com.google.common.collect.Iterables;
import net.sf.anathema.hero.attributes.advance.CreationPointSumComparator;
import net.sf.anathema.hero.traits.model.TraitGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AttributeGroupList {

  private final List<TraitGroup> attributeGroups;
  private final IncrementCounter incrementCounter;

  public AttributeGroupList(TraitGroup[] traitGroup, IncrementCounter incrementCounter) {
    this(Arrays.asList(traitGroup), incrementCounter);
  }

  public AttributeGroupList(Collection<TraitGroup> traitGroup, IncrementCounter incrementCounter) {
    this.incrementCounter = incrementCounter;
    attributeGroups = new ArrayList<>(traitGroup);
  }

  public void remove(TraitGroup group) {
    attributeGroups.remove(group);
  }

  public AttributeGroupList getGroupsThatSpendXOrMorePoints(int points) {
    List<TraitGroup> exceedingGroups = attributeGroups.stream().filter(traitGroup -> incrementCounter.getIncrementCount(traitGroup) >= points).collect(toList());
    return new AttributeGroupList(exceedingGroups, incrementCounter);
  }

  public TraitGroup findGroupWithMaximalIncrements() {
    attributeGroups.sort(new CreationPointSumComparator());
    return Iterables.getLast(attributeGroups);
  }

  public boolean hasElements() {
    return !attributeGroups.isEmpty();
  }

  public TraitGroup findGroupWithMinimalIncrements() {
    attributeGroups.sort(new CreationPointSumComparator());
    return attributeGroups.get(0);
  }
}