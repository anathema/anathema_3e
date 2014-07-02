package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;

import java.util.Map;

public interface CharmLearnPrerequisite {

	void link(Map<CharmName, CharmImpl> charmsById);
	
	boolean isSatisfied(ICharmLearnArbitrator arbitrator);
	
	boolean isAutoSatisfiable(ICharmLearnableArbitrator arbitrator);
}
