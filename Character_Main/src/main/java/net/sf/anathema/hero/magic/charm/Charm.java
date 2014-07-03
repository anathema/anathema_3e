package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.charm.data.Duration;
import net.sf.anathema.charm.data.CharmType;

import java.util.function.Consumer;

public interface Charm extends Magic {

  CharmName getName();

  TreeReference getTreeReference();

  CharmType getCharmType();

  Duration getDuration();

  void forEachChild(Consumer<Charm> consumer);

  PrerequisiteList getPrerequisites();
}