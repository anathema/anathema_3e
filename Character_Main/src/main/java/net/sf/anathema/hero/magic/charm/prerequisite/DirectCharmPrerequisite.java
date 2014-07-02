package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;

public interface DirectCharmPrerequisite extends CharmPrerequisite {
	Charm[] getDirectPredecessors();
	
	Charm[] getLearnPrerequisites(ICharmLearnArbitrator arbitrator);
}
