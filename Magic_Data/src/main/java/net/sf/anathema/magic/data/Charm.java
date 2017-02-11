package net.sf.anathema.magic.data;

import net.sf.anathema.magic.data.prerequisite.RequiredTraitType;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.magic.data.reference.TreeReference;

import java.util.function.Consumer;

public interface Charm extends Magic {

  CharmName getName();

  TreeReference getTreeReference();

  CharmType getCharmType();

  Duration getDuration();

  void forEachChild(Consumer<Charm> consumer);

  PrerequisiteList getPrerequisites();

  /** Gets the trait type that this Charm should generally be associated with for tests of "Charms from X group". */
  RequiredTraitType getPrimaryTraitType();
}