package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.TraitLimitation;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface SpiritualTraitModel extends TraitMap, HeroModel {

  Identifier ID = new SimpleIdentifier("SpiritualTraits");

  int getEssenceCap(boolean modified);

  TraitLimitation getEssenceLimitation();
}
