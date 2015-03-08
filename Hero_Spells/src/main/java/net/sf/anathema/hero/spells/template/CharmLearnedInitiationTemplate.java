package net.sf.anathema.hero.spells.template;

import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("charm")
public class CharmLearnedInitiationTemplate implements CircleInitiationTemplate {
	public String charmId;
	
	@Override
	public boolean isInitiated(Hero hero) {
		return CharmsModelFetcher.fetch(hero).isLearned(new CharmName(charmId));
	}
}
