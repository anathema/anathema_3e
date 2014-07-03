package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

import java.util.List;
import java.util.function.Consumer;

public interface PrerequisiteList {

  TraitType getPrimaryTraitType();

  ValuedTraitType getEssence();

  ValuedTraitType[] getTraitPrerequisites();

  void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer);

  List<CharmPrerequisite> getCharmPrerequisites();
}
