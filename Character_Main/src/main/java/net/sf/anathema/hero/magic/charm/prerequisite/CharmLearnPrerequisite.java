package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.magic.charm.UnlinkedCharmMap;

public interface CharmLearnPrerequisite {

  void accept(CharmPrerequisiteVisitor visitor);

  void link(UnlinkedCharmMap unlinkedCharms);

  boolean isSatisfied(ICharmLearnArbitrator arbitrator);

  boolean isAutoSatisfiable(ICharmLearnableArbitrator arbitrator);
}
