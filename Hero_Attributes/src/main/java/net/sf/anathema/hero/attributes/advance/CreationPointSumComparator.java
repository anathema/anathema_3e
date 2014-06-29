package net.sf.anathema.hero.attributes.advance;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;

import java.util.Comparator;

public class CreationPointSumComparator implements Comparator<TraitGroup> {

  @Override
  public int compare(TraitGroup o1, TraitGroup o2) {
    return countPoints(o1).compareTo(countPoints(o2));
  }

  private Integer countPoints(TraitGroup group) {
    int count = 0;
    for (Trait trait : group.getGroupTraits()) {
      count += trait.getCreationValue();
    }
    return count;
  }
}
