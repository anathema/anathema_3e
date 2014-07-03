package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.RequiredTraitType;
import net.sf.anathema.hero.magic.charm.prerequisite.TraitPrerequisite;

import java.util.List;
import java.util.function.Consumer;

public interface PrerequisiteList {

  RequiredTraitType getPrimaryTraitType();

  List<TraitPrerequisite> getTraitPrerequisites();

  void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer);

  List<CharmPrerequisite> getCharmPrerequisites();
}
