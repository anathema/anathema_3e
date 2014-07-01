package net.sf.anathema.hero.magic.charm.prerequisite;

import java.util.Map;

import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;

public interface CharmLearnPrerequisite {

	void link(Map<String, CharmImpl> charmsById);
	
	boolean isSatisfied(ICharmLearnArbitrator arbitrator);
	
	boolean isAutoSatisfiable(ICharmLearnableArbitrator arbitrator);
}
