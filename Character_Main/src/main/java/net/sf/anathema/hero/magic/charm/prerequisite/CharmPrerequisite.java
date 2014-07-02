package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.magic.charm.UnlinkedCharmMap;

public interface CharmPrerequisite {

  void accept(CharmPrerequisiteVisitor visitor);

  void link(UnlinkedCharmMap unlinkedCharms);

  boolean isAutoSatisfiable(ICharmLearnableArbitrator arbitrator);
}
