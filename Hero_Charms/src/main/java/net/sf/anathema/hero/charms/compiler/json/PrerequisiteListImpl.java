package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.magic.data.PrerequisiteList;
import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.magic.data.prerequisite.RequiredTraitType;
import net.sf.anathema.magic.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.magic.template.CharmTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PrerequisiteListImpl implements PrerequisiteList {

  public static final String ESSENCE_ID = "Essence";
  private final List<CharmPrerequisite> charmPrerequisites = new ArrayList<>();
  private final List<TraitPrerequisite> traitPrerequisites = new ArrayList<>();

  public PrerequisiteListImpl(CharmTemplate template) {
    template.minimums.forEach((type, value) -> traitPrerequisites.add(new TraitPrerequisite(new RequiredTraitType(type), value)));
    if (!template.minimums.containsKey(ESSENCE_ID)) {
      traitPrerequisites.add(new TraitPrerequisite(new RequiredTraitType(ESSENCE_ID), 1));
    }
    traitPrerequisites.sort((trait, otherTrait) -> (otherTrait.type.type.equals(ESSENCE_ID) ? -1 : trait.type.type.compareTo(otherTrait.type.type)));
  }

  @Override
  public RequiredTraitType getPrimaryTraitType() {
    return traitPrerequisites.get(0).type;
  }

  @Override
  public void forEachTraitPrerequisite(Consumer<TraitPrerequisite> consumer) {
    traitPrerequisites.forEach(consumer);
  }

  @Override
  public void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer) {
    charmPrerequisites.forEach(consumer);
  }

  @Override
  public boolean hasCharmPrerequisites() {
    return !charmPrerequisites.isEmpty();
  }

  public void addCharmPrerequisite(CharmPrerequisite prerequisite) {
    charmPrerequisites.add(prerequisite);
  }

  public void clearPrerequisites() {
    charmPrerequisites.clear();
  }
}