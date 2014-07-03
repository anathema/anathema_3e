package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmLearnArbitrator;

public interface DirectCharmPrerequisite extends CharmPrerequisite {

	Charm[] getDirectPredecessors();
	
	Charm[] getLearnPrerequisites(CharmLearnArbitrator arbitrator);
}
