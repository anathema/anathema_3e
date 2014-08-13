package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.rules.ModificationType;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.hero.traits.template.LimitationType;
import net.sf.anathema.hero.traits.template.TraitTemplate;

import com.google.common.base.Strings;

public class MeritImpl extends TraitImpl implements Merit {

	private final MeritOption optionBase;
	private final String description;
	
	public MeritImpl(MeritOption base, String description, Hero hero) {
		super(hero, createTraitRules(base, hero));
		this.optionBase = base;
		this.description = description;
	}
	
	private static TraitRules createTraitRules(MeritOption base, Hero hero) {
		TraitTemplate template = createTraitTemplate(base);
		TraitRules rules = new MeritRulesImpl(base, template, hero);
		return rules;
	}
	
	private static TraitTemplate createTraitTemplate(MeritOption base) {
		TraitTemplate template = new TraitTemplate();
		LimitationTemplate limitation = new LimitationTemplate();
	    limitation.type = LimitationType.Static;
	    limitation.value = base.getMaximumValue();
		template.minimumValue = base.getMinimumValue();
		template.startValue = template.minimumValue;
		template.modificationType = base.getType() == MeritCategory.Purchased ?
				ModificationType.RaiseOnly : ModificationType.Free;
		template.limitation = limitation;
		return template;
	}

	@Override
	public MeritOption getBaseOption() {
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
}
