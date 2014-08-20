package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.template.CircleInitiationTemplate;
import net.sf.anathema.hero.spells.template.SpellsTemplate;

public class SorceryInitiationEvaluator {
	private final Hero hero;
	private final SpellsTemplate template;
	
	public SorceryInitiationEvaluator(Hero hero, SpellsTemplate template) {
		this.hero = hero;
		this.template = template;
	}
	
	public boolean isInitiated(CircleType circle) {
		return isMeritInitiated(circle) || isCharmInitiated(circle);
	}
	
	public boolean isMeritInitiated(CircleType circle) {
		CircleInitiationTemplate initiation = template.meritInitiations.get(circle);
		if (initiation == null) {
			return false;
		}
		return initiation.isInitiated(hero);
	}
	
	public boolean isCharmInitiated(CircleType circle) {
		CircleInitiationTemplate initiation = template.charmInitiations.get(circle);
		if (initiation == null) {
			return false;
		}
		return initiation.isInitiated(hero);
	}
}
