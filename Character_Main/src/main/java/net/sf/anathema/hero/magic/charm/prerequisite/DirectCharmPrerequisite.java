package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.hero.magic.charm.Charm;

public interface DirectCharmPrerequisite extends CharmPrerequisite {

	Charm[] getDirectPredecessors();
}
