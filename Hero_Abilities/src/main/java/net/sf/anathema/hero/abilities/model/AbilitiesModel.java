package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.GroupedTraitsModel;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.List;

public interface AbilitiesModel extends GroupedTraitsModel {

  Identifier ID = new SimpleIdentifier("Abilities");

  int getTraitPicksForState(TraitStateType state);

  Iterable<TraitStateType> getAvailableTraitStates();

  List<TraitType> getAllAbilityTypes();
}
