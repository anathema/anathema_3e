package net.sf.anathema.hero.traits.model;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.prerequisite.RequiredTraitType;
import net.sf.anathema.magic.data.prerequisite.TraitPrerequisite;

public class TraitTypeUtils {

  public TraitType getTraitTypeFor(TraitPrerequisite prerequisite) {
    return new TraitType(prerequisite.type.type);
  }

  public TraitType getPrimaryTraitType(Charm charm) {
    RequiredTraitType primaryTraitType = charm.getPrerequisites().getPrimaryTraitType();
    return new TraitType(primaryTraitType.type);
  }
}