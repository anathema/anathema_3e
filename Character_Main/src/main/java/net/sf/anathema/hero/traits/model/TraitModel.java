package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.model.rules.limitation.TraitLimitation;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface TraitModel extends TraitIterable, TraitMap {

  Identifier ID = new SimpleIdentifier("OverallTraitModel");

  void addTraits(Trait... traits);

  TraitValueStrategy getValueStrategy();

  TraitLimitation createLimitation(LimitationTemplate limitation);
}
