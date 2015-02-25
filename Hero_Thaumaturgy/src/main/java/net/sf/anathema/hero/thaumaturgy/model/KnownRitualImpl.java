package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.rules.ModificationType;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.hero.traits.template.LimitationType;
import net.sf.anathema.hero.traits.template.TraitTemplate;

import com.google.common.base.Strings;

public class KnownRitualImpl extends TraitImpl implements KnownRitual {

  private final ThaumaturgyRitual optionBase;
  private final String description;

  public KnownRitualImpl(ThaumaturgyRitual base, String description, Hero hero) {
    super(hero, createTraitRules(base, hero));
    this.optionBase = base;
    this.description = description;
  }

  private static TraitRules createTraitRules(ThaumaturgyRitual base, Hero hero) {
    TraitTemplate template = createTraitTemplate(base);
    return new RitualRulesImpl(base, template, hero);
  }

  private static TraitTemplate createTraitTemplate(ThaumaturgyRitual base) {
    TraitTemplate template = new TraitTemplate();
    LimitationTemplate limitation = new LimitationTemplate();
    limitation.type = LimitationType.Static;
    limitation.value = base.getMaximumValue();
    template.minimumValue = base.getMinimumValue();
    template.startValue = template.minimumValue;
    template.modificationType = ModificationType.RaiseOnly;
    template.limitation = limitation;
    return template;
  }

  @Override
  public ThaumaturgyRitual getBaseOption() {
    return optionBase;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return optionBase.getId() + (!Strings.isNullOrEmpty(description) ? " (" + description + ")" : "");
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