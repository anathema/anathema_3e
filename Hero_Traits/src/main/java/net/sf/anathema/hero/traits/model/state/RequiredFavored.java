package net.sf.anathema.hero.traits.model.state;

import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.DefaultTraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;

public class RequiredFavored implements RequiredTraitState {

  @Override
  public boolean satisfiesRequirement(TraitStateType newState) {
    return !newState.countsAs(Caste);
  }

  @Override
  public TraitStateType overrideStateIfNecessary(TraitStateType state) {
    if (state.countsAs(Default)) {
      return Favored;
    }
    return state;
  }
}
