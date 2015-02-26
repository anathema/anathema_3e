package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.rules.ModificationType;
import net.sf.anathema.library.model.AbstractKnownOptionalTrait;

public class KnownRitualImpl extends AbstractKnownOptionalTrait<ThaumaturgyRitual> implements KnownRitual {

  public KnownRitualImpl(ThaumaturgyRitual base, String description, Hero hero) {
    super(hero, base, createTraitRules(base, hero, ModificationType.RaiseOnly), description);
  }

	@Override
	public RitualLevel getLevel() {
		switch (getCurrentValue()) {
		case ThaumaturgyRitual.BASIC_RITUAL_LEVEL:
	  default:
			return RitualLevel.Basic;
		case ThaumaturgyRitual.ADVANCED_RITUAL_LEVEL:
			return RitualLevel.Advanced;
		}
	}
}