package net.sf.anathema.charm.data;

import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitPrerequisite;

import java.util.List;
import java.util.function.Consumer;

public interface PrerequisiteList {

  RequiredTraitType getPrimaryTraitType();

  List<TraitPrerequisite> getTraitPrerequisites();

  void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer);

  List<CharmPrerequisite> getCharmPrerequisites();
}
