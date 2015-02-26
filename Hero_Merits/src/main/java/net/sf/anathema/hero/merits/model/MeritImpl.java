package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.rules.ModificationType;
import net.sf.anathema.library.model.AbstractKnownOptionalTrait;

public class MeritImpl extends AbstractKnownOptionalTrait<MeritOption> implements Merit {

  public MeritImpl(MeritOption base, String description, Hero hero,
                   boolean isExperienced) {
    super(hero, base, createTraitRules(base, hero, base.getCategory() == MeritCategory.Purchased ?
        ModificationType.RaiseOnly : ModificationType.Free), description);
    
    if (isExperienced) {
      this.setExperiencedValue(getCreationValue());
      this.setUncheckedCreationValue(0);
    }
  }
}