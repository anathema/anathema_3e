package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.state.Castes;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.RequiredTraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
import net.sf.anathema.hero.traits.model.state.TraitStateImpl;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.state.TraitStateTypes;
import net.sf.anathema.hero.traits.model.types.CommonTraitTypes;

import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.DefaultTraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;
import static net.sf.anathema.hero.traits.model.state.SupernalTraitStateType.Supernal;

/** Extension of TraitStateImpl that handles the special Favored/Caste/Supernal relationship MA has with Brawl. */
public class MartialArtsTraitStateImpl extends TraitStateImpl {

  private TraitStateMap states;

  public MartialArtsTraitStateImpl(Hero hero, Castes traitCastes, MappableTypeIncrementChecker<TraitStateType> checker,
                                   RequiredTraitState requiredState, TraitStateTypes traitStateTypes, TraitStateMap states) {
    super(hero, traitCastes, checker, requiredState, traitStateTypes);
    this.states = states;
  }

  @Override
  public boolean isSelectableForState(TraitStateType state) {
    TraitStateType brawlState = states.getState(CommonTraitTypes.Brawl).getType();
    return super.isSelectableForState(state) && (brawlState.countsAs(state) || state.countsAs(brawlState)); // true iff the state and brawl's are mutually compatible
  }

  @Override
  public boolean countsTowardsLimitsOn(TraitStateType type) {
    return type.countsAs(Supernal); // MA only counts towards the limit on Supernal picks, not Favored/Caste
  }

  /** Intended to be added to a listener for Brawl state changes. Changes MA's state to be consistent with the new Brawl choice. */
  public void onBrawlStateChange(TraitStateType brawlState) {
    if (brawlState.countsAs(Default)) { // if brawl has been set to non-caste/favored, we must follow, incl. losing supernal
      super.changeStateTo(Default);
    } else if (brawlState.countsAs(Supernal)) { // if brawl has been set to supernal we must be a flavor of caste
      if (getType().countsAs(Caste)) {
        // we are already a flavor of caste; do nothing
      } else {
        super.changeStateTo(Caste);
      }
    } else if (brawlState.countsAs(Favored)) { // if brawl has been set to favored (i.e. NOT caste), must follow them - incl. losing supernal!
      super.changeStateTo(Favored);
    } else if (brawlState.countsAs(Caste)) { // if brawl has been set to caste, should follow
      if (getType().countsAs(Supernal)) {
        // do nothing, we can remain supernal - rely on the Supernal limit to prevent double-Supernal (if Supernal limit is 1)
      } else {
        super.changeStateTo(Caste);
      }
    } else {
      throw new IllegalArgumentException("Unknown Brawl state encountered: " + brawlState.getId() + ". Martial Arts rules for this state are unknown.");
    }
  }
}