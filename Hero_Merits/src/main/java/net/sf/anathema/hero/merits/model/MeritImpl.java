package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.hero.traits.model.rules.ModificationType;
import net.sf.anathema.library.model.trait.AbstractPossessedOptionalTrait;

public class MeritImpl extends AbstractPossessedOptionalTrait<MeritOption> implements Merit {

  public MeritImpl(MeritOption base, String description, Hero hero,
                   boolean isExperienced) {
    super(hero, base, createTraitRules(base, hero, base.getCategory() == MeritCategory.Purchased ?
            ModificationType.RaiseOnly : ModificationType.Free), description);

    if (isExperienced) {
      this.setExperiencedValue(getCreationValue());
      this.setUncheckedCreationValue(0);
    }
  }

  @Override
  public boolean hasMechanicalDetail(MechanicalDetailReference reference) {
    for (MechanicalDetail detail : getBaseOption().getMechanics()) {
      if (detail.isReferencedBy(reference)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public MechanicalDetail getMechanicalDetail(MechanicalDetailReference reference) {
    for (MechanicalDetail detail : getBaseOption().getMechanics()) {
      if (detail.isReferencedBy(reference)) {
        return detail;
      }
    }
    throw new IllegalArgumentException("Check available details via ``hasMechanicalDetail`` before requesting.");
  }
}