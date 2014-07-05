package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.TraitListModel;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface AbilitiesModel extends TraitListModel {

  Identifier ID = new SimpleIdentifier("Abilities");

  int getTraitPicksForState(TraitState state);
}
