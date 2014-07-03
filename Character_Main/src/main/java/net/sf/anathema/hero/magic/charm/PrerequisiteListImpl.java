package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class PrerequisiteListImpl implements PrerequisiteList {

  private final ValuedTraitType essencePrerequisite;
  private final ValuedTraitType[] traitPrerequisites;
  private final List<CharmPrerequisite> prerequisites = new ArrayList<>();

  public PrerequisiteListImpl(CharmPrerequisiteList prerequisiteList) {
    this.essencePrerequisite = prerequisiteList.getEssencePrerequisite();
    this.traitPrerequisites = prerequisiteList.getTraitPrerequisites();
    prerequisites.addAll(asList(prerequisiteList.getCharmPrerequisites()));
  }

  @Override
  public ValuedTraitType getEssence() {
    return essencePrerequisite;
  }

  @Override
  public ValuedTraitType[] getTraitPrerequisites() {
    return traitPrerequisites;
  }

  @Override
  public void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer) {
    prerequisites.forEach(consumer);
  }

  @Override
  public List<CharmPrerequisite> getCharmPrerequisites() {
    return prerequisites;
  }
}
