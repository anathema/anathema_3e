package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.attributes.template.AttributePointsTemplate;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;

public class IncrementCounter {

  private final AttributePointsTemplate template;

  public IncrementCounter(AttributePointsTemplate template) {
    this.template = template;
  }

  public Integer getIncrementCount(TraitGroup group) {
    int count = 0;
    for (Trait trait : group.getGroupTraits()) {
      count += trait.getCreationValue() - template.standard.calculationBase;
    }
    return count;
  }
}