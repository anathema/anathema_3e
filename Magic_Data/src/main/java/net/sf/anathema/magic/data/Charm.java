package net.sf.anathema.magic.data;

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
}