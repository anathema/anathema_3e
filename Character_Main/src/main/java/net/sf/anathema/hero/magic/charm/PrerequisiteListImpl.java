package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static net.sf.anathema.hero.traits.model.types.OtherTraitType.Essence;

public class PrerequisiteListImpl implements PrerequisiteList {

  private final ValuedTraitType essence;
  private final ValuedTraitType[] traits;
  private final List<CharmPrerequisite> charms = new ArrayList<>();
  private final TraitType primaryTraitType;

  public PrerequisiteListImpl(CharmPrerequisiteList prerequisiteList) {
    this.essence = prerequisiteList.getEssencePrerequisite();
    this.traits = prerequisiteList.getTraitPrerequisites();
    charms.addAll(asList(prerequisiteList.getCharmPrerequisites()));
    this.primaryTraitType = traits.length == 0 ? Essence : traits[0].getType();
  }

  @Override
  public ValuedTraitType getEssence() {
    return essence;
  }

  @Override
  public ValuedTraitType[] getTraitPrerequisites() {
    return traits;
  }

  @Override
  public void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer) {
    charms.forEach(consumer);
  }

  @Override
  public List<CharmPrerequisite> getCharmPrerequisites() {
    return charms;
  }

  @Override
  public TraitType getPrimaryTraitType() {
    return primaryTraitType;
  }
}
