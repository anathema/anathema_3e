package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;

public interface TraitListModel extends TraitMap, TraitStateMap, HeroModel {

  TraitState getTraitState(TraitType traitType);

  TraitState getTraitState(Trait trait);

  IdentifiedTraitTypeList[] getTraitTypeList();

  int getTraitMaximum();
}
