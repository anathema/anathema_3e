package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.hero.magic.charm.UnlinkedCharmMap;

public interface CharmPrerequisite {

  void process(PrerequisiteProcessor processor);

  void accept(PrerequisiteVisitor visitor);

  void link(UnlinkedCharmMap unlinkedCharms);
}
