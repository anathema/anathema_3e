package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

public class TraitCollectionUtilities {

  public static Trait getEssence(TraitMap traitConfiguration) {
    return traitConfiguration.getTrait(OtherTraitType.Essence);
  }

  public static Trait getWillpower(TraitMap traitConfiguration) {
    return traitConfiguration.getTrait(OtherTraitType.Willpower);
  }
}