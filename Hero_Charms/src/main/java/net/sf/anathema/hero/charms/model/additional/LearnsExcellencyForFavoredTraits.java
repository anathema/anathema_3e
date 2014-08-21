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
				new ExcellencyMonitor(trait)));
		abilities.getAll().forEach(trait -> trait.addCurrentValueListener(new ExcellencyMonitor(trait)));
	}
	
	private class ExcellencyMonitor implements TraitStateChangedListener, IntegerChangedListener {
		private final Trait trait;
		
		public ExcellencyMonitor(Trait trait) {
			this.trait = trait;
		}

		@Override
		public void favorableStateChanged(TraitStateType state) {
			Charm excellency = charms.getCharmById(new CharmName(getStringForExcellency(trait.getType().getId())));
			if (trait.getCurrentValue() > 0 && (state.countsAs(Caste) || state.countsAs(Favored))) {
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
			Charm excellency = charms.getCharmById(new CharmName(getStringForExcellency(trait.getType().getId())));
			if (newValue == 0 && charms.isLearned(excellency)) {
				charms.getLearningModel().forgetCharm(excellency, false);
			}
			
		}
	}
}
