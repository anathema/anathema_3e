package net.sf.anathema.hero.traits.model.lists;

import net.sf.anathema.hero.traits.model.TraitType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DefaultTraitTypeList implements TraitTypeList {

  private final List<TraitType> traitTypes = new ArrayList<>();

  public DefaultTraitTypeList(TraitType[] traitTypes) {
    Collections.addAll(this.traitTypes, traitTypes);
  }

  public DefaultTraitTypeList(Collection<TraitType> traitTypes) {
    this.traitTypes.addAll(traitTypes);
  }

  @Override
  public final TraitType getById(String typeId) {
    for (TraitType element : traitTypes) {
      if (element.getId().equals(typeId)) {
        return element;
      }
    }
    throw new IllegalArgumentException("No trait type with found in group with id " + typeId);
  }

  @Override
  public List<TraitType> getAll() {
    return new ArrayList<>(traitTypes);
  }

  @Override
  public int size() {
    return traitTypes.size();
  }

  @Override
  public final boolean contains(TraitType traitType) {
    return traitTypes.contains(traitType);
  }
}