package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;

public class IncrementCounter {

  private AttributeCreationData creationData;

  public IncrementCounter(AttributeCreationData creationData) {
    this.creationData = creationData;
  }

  public Integer getIncrementCount(TraitGroup group) {
    int count = 0;
    for (Trait trait : group.getGroupTraits()) {
      count += trait.getCreationValue() - creationData.getCalculationBase(trait.getType());
    }
    return count;
  }
}