package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.List;

public class MappedTraitGroup implements TraitGroup {
  private final TraitMap traitMap;
  private final IdentifiedTraitTypeList typeGroup;

  public MappedTraitGroup(TraitMap traitMap, IdentifiedTraitTypeList typeGroup) {
    this.traitMap = traitMap;
    this.typeGroup = typeGroup;
  }

  @Override
  public Trait[] getGroupTraits() {
    List<Trait> traits = new ArrayList<>();
    for (TraitType type : typeGroup.getAll()) {
      traits.add(traitMap.getTrait(type));
    }
    return traits.toArray(new Trait[traits.size()]);
  }

  @Override
  public Identifier getGroupId() {
    return typeGroup.getListId();
  }
}
