package net.sf.anathema.magic.data;

import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.magic.data.prerequisite.RequiredTraitType;
import net.sf.anathema.magic.data.prerequisite.TraitPrerequisite;

import java.util.function.Consumer;

public interface PrerequisiteList {

  /** Gets the largest non-essenced required trait type, or Essence if there are none. */
  RequiredTraitType getPrimaryRequiredTraitType();

  void forEachTraitPrerequisite(Consumer<TraitPrerequisite> consumer);

  void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer);

  boolean hasCharmPrerequisites();
}