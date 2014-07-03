package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.data.PrerequisiteList;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.charm.template.CharmTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PrerequisiteListImpl implements PrerequisiteList {

  public static final String ESSENCE_ID = "Essence";
  private final List<CharmPrerequisite> charmPrerequisites = new ArrayList<>();
  private final List<TraitPrerequisite> traitPrerequisites = new ArrayList<>();

  public PrerequisiteListImpl(CharmTemplate template) {
    template.minimums.forEach((type, value) -> {
      traitPrerequisites.add(new TraitPrerequisite(new RequiredTraitType(type), value));
    });
    if (!template.minimums.containsKey(ESSENCE_ID)) {
      traitPrerequisites.add(new TraitPrerequisite(new RequiredTraitType(ESSENCE_ID), 1));
    }
  }

  @Override
  public RequiredTraitType getPrimaryTraitType() {
    return traitPrerequisites.get(0).type;
  }

  @Override
  public List<TraitPrerequisite> getTraitPrerequisites() {
    return new ArrayList<>(traitPrerequisites);
  }

  @Override
  public void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer) {
    charmPrerequisites.forEach(consumer);
  }

  @Override
  public List<CharmPrerequisite> getCharmPrerequisites() {
    return new ArrayList<>(charmPrerequisites);
  }

  public void addCharmPrerequisite(CharmPrerequisite prerequisite) {
    charmPrerequisites.add(prerequisite);
  }
}
