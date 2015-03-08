package net.sf.anathema.hero.spells.template;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.platform.persistence.JsonField;

@JsonField("type")
public interface CircleInitiationTemplate {
	boolean isInitiated(Hero hero);
}