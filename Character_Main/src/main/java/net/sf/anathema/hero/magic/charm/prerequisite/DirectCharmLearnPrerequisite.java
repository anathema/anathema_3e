package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;

public interface DirectCharmLearnPrerequisite extends CharmLearnPrerequisite {
	Charm[] getDirectPredecessors();
	
	Charm[] getLearnPrerequisites(ICharmLearnArbitrator arbitrator);
}
