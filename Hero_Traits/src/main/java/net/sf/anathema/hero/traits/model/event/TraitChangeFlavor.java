package net.sf.anathema.hero.traits.model.event;

import net.sf.anathema.hero.individual.model.change.ChangeFlavor;
import net.sf.anathema.hero.traits.model.TraitType;

public class TraitChangeFlavor extends ChangeFlavor {

  private TraitType traitType;

  public TraitChangeFlavor(TraitType traitType) {
    super(traitType.getId());
    this.traitType = traitType;
  }

  public TraitType getTraitType() {
    return traitType;
  }

  public static boolean changes(ChangeFlavor flavor, TraitType... typeArray) {
    if (!(flavor instanceof TraitChangeFlavor)) {
      return false;
    }
    TraitType changeType = ((TraitChangeFlavor) flavor).getTraitType();
    for (TraitType type : typeArray) {
      if (type.equals(changeType)) {
        return true;
      }
    }
    return false;
  }
}
