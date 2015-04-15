package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Willpower;

public class TraitCollectionUtilities {

  public static Trait getWillpower(TraitMap traitConfiguration) {
    return traitConfiguration.getTrait(Willpower);
  }
}