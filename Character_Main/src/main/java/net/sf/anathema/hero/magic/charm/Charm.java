package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.magic.basic.Magic;
import net.sf.anathema.hero.magic.charm.duration.Duration;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface Charm extends Magic {

  String FAVORED_CASTE_PREFIX = "FavoredCaste.";

  CharmName getName();

  TreeReference getTreeReference();

  CharmType getCharmType();

  Duration getDuration();

  TraitType getPrimaryTraitType();

  ValuedTraitType getEssence();

  ValuedTraitType[] getPrerequisites();

  List<CharmPrerequisite> getCharmPrerequisites();

  void forEachChild(Consumer<Charm> consumer);

  void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer);

  Set<Charm> getPrerequisiteCharms(ICharmLearnArbitrator learnArbitrator);
}