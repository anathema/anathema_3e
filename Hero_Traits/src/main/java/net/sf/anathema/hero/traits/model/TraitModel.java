package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.model.rules.minimum.DynamicMinimumMap;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface TraitModel extends TraitIterable, TraitMap {

  Identifier ID = new SimpleIdentifier("OverallTraitModel");

  void addTraits(Trait... traits);

  void addTraits(Iterable<Trait> traits);

  TraitValueStrategy getValueStrategy();

  TraitLimitation createLimitation(LimitationTemplate limitation);

  DynamicMinimumMap getMinimumMap();
}
