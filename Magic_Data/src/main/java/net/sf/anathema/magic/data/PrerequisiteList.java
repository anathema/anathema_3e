package net.sf.anathema.magic.data;

import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.magic.data.prerequisite.RequiredTraitType;
import net.sf.anathema.magic.data.prerequisite.TraitPrerequisite;

import java.util.function.Consumer;

public interface PrerequisiteList {

  RequiredTraitType getPrimaryTraitType();

  void forEachTraitPrerequisite(Consumer<TraitPrerequisite> consumer);

  void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer);

  boolean hasCharmPrerequisites();
}