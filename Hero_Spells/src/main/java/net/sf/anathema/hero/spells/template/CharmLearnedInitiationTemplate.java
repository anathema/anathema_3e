package net.sf.anathema.hero.spells.template;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("charm")
public class CharmLearnedInitiationTemplate implements CircleInitiationTemplate {
	public CircleType circle;
	public String charmId;
	
	@Override
	public boolean isInitiated(Hero hero) {
		return CharmsModelFetcher.fetch(hero).isLearned(new CharmName(charmId));
	}
}
