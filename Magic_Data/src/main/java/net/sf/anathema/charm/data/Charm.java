package net.sf.anathema.charm.data;

import java.util.function.Consumer;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.magic.data.Magic;

public interface Charm extends Magic {

  CharmName getName();

  TreeReference getTreeReference();

  CharmType getCharmType();

  Duration getDuration();

  void forEachChild(Consumer<Charm> consumer);

  PrerequisiteList getPrerequisites();
}