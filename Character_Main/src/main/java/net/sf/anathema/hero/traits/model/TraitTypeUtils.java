package net.sf.anathema.hero.traits.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

import java.util.ArrayList;

import static java.util.Collections.addAll;

public class TraitTypeUtils {

  private final ArrayList<TraitType> allPrerequisiteTypeList = new ArrayList<>();

  public TraitTypeUtils() {
    addAll(allPrerequisiteTypeList, AbilityType.values());
    addAll(allPrerequisiteTypeList, AttributeType.values());
    addAll(allPrerequisiteTypeList, OtherTraitType.values());
  }

  public TraitType getTraitTypeById(String id) {
    for (TraitType type : allPrerequisiteTypeList) {
      if (id.equals(type.getId())) {
        return type;
      }
    }
    throw new IllegalArgumentException("No trait type with id: " + id);
  }

  public TraitType getTraitTypeFor(TraitPrerequisite prerequisite) {
    return getTraitTypeById(prerequisite.type.type);
  }

  public TraitType getPrimaryTraitType(Charm charm) {
    RequiredTraitType primaryTraitType = charm.getPrerequisites().getPrimaryTraitType();
    return getTraitTypeById(primaryTraitType.type);
  }
}