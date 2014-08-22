package net.sf.anathema.hero.charms.model.additional;

import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateChangedListener;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.event.IntegerChangedListener;

public class LearnsExcellencyForFavoredTraits extends ExcellencyAdditionalRules {

	private final static String id = "LearnsExcellencyForLearnedFavoredTraits";
	
	private final CharmsModel charms;
	private final Hero hero;
	
	public LearnsExcellencyForFavoredTraits(CharmsModel charms, Hero hero) {
		super(hero.getSplat().getTemplateType().getHeroType());
		this.charms = charms;
		this.hero = hero;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void initialize() {
		// TODO: Favorable traits other than abilities
		AbilitiesModel abilities = AbilitiesModelFetcher.fetch(hero);
		abilities.getAll().forEach(trait -> abilities.getState(trait).addTraitStateChangedListener(
				new ExcellencyMonitor(trait, abilities.getState(trait))));
		abilities.getAll().forEach(trait -> trait.addCurrentValueListener(new ExcellencyMonitor(trait, abilities.getState(trait))));
	}
	
	private class ExcellencyMonitor implements TraitStateChangedListener, IntegerChangedListener {
		private final Trait trait;
		private boolean isFavored;
		
		public ExcellencyMonitor(Trait trait, TraitState initialState) {
			this.trait = trait;
			this.isFavored = isEligible(initialState.getType());
		}
		
		private boolean isEligible(TraitStateType state) {
			return state.countsAs(Caste) || state.countsAs(Favored);
		}

		@Override
		public void favorableStateChanged(TraitStateType newState) {
			Charm excellency = charms.getCharmById(new CharmName(getStringForExcellency(trait.getType().getId())));
			isFavored = isEligible(newState);
			if (trait.getCurrentValue() > 0 && isFavored) {
				if (!charms.isLearned(excellency)) {
					charms.getLearningModel().learnCharm(excellency, false);
				}
			} else {
				if (charms.isLearned(excellency)) {
					charms.getLearningModel().forgetCharm(excellency, false);
				}
			}
		}

		@Override
		public void valueChanged(int newValue) {
			if (isFavored) {
				Charm excellency = charms.getCharmById(new CharmName(getStringForExcellency(trait.getType().getId())));
				if (newValue == 0 && charms.isLearned(excellency)) {
					charms.getLearningModel().forgetCharm(excellency, false);
				}
				if (newValue > 0 && !charms.isLearned(excellency)) {
					charms.getLearningModel().learnCharm(excellency, false);
				}
			}
		}
	}
}
