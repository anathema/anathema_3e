package net.sf.anathema.hero.charms.model.special.mechanics;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearningModel;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.hero.individual.model.Hero;

public abstract class AbstractCharmMechanic implements CharmSpecialMechanic {
	private final CharmName charmId;
	
	protected AbstractCharmMechanic(CharmName id) {
		this.charmId = id;
	}
	
	protected boolean knowsCharm(Hero hero) {
		return getLearnCount(hero) > 0;
	}
	
	protected int getLearnCount(Hero hero) {
		CharmsModel charms = CharmsModelFetcher.fetch(hero);
		Charm baseCharm = charms.getCharmById(charmId);
		CharmSpecialLearningModel learning = charms.getCharmSpecialLearningModel(baseCharm);
		return learning != null ? learning.getCurrentLearnCount() : (charms.isLearned(baseCharm) ? 1 : 0);
	}
}
