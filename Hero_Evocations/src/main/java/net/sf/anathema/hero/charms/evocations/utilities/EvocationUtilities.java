package net.sf.anathema.hero.charms.evocations.utilities;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.template.evocations.EvocationTier;

public class EvocationUtilities {

  public static EvocationTier getTier(Charm charm) {
    if (charm.hasAttribute(EvocationTier.Emerald)) {
      return EvocationTier.Emerald;
    }
    if (charm.hasAttribute(EvocationTier.Sapphire)) {
      return EvocationTier.Sapphire;
    }
    if (charm.hasAttribute(EvocationTier.Adamant)) {
      return EvocationTier.Adamant;
    }
    return EvocationTier.None;
  }
}