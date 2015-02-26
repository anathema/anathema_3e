package net.sf.anathema.library.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.template.TraitTemplate;

public class OptionalTraitRulesImpl<O extends OptionalTraitOption> extends TraitRulesImpl {

  private final O base;

  public OptionalTraitRulesImpl(O base, TraitTemplate template, Hero hero) {
    super(base, template, hero);
    this.base = base;
  }

  @Override
  public int getExperiencedValue(int creationValue, int demandedValue) {
    if (!isReducible() && demandedValue < creationValue) {
      return creationValue;
    }
    if (!base.isLegalValue(demandedValue)) {
      return getNearestLegalValue(demandedValue);
    }
    return demandedValue;
  }

  @Override
  public int getCreationValue(int demandedValue) {
    if (!base.isLegalValue(demandedValue)) {
      return getNearestLegalValue(demandedValue);
    }
    return demandedValue;
  }

  private int getNearestLegalValue(int demandedValue) {
    for (int i = 0; i <= base.getMaximumValue(); i++) {
      if (base.isLegalValue(demandedValue - i)) {
        return demandedValue - i;
      }
      if (base.isLegalValue(demandedValue + i)) {
        return demandedValue + i;
      }
    }
    return 0;
  }
}