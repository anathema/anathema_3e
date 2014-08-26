package net.sf.anathema.hero.charms.model.additional;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnListener;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.TraitType;

public class LearnsExcellencyWithOtherCharms extends ExcellencyAdditionalRules {

	private final static String id = "LearnsExcellencyWithOtherCharm";
	
	private final CharmsModel charms;
	
	public LearnsExcellencyWithOtherCharms(CharmsModel charms, Hero hero) {
		super(hero.getSplat().getTemplateType().getHeroType());
		this.charms = charms;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void initialize() {
		charms.addCharmLearnListener(new ExcellencyMonitor());
	}
	
	private class ExcellencyMonitor implements ICharmLearnListener {
		
		private boolean hasOtherCharmsOfTrait(String trait) {
			TraitType traitType = new TraitTypeFinder().getTrait(trait);
			List<TraitType> traitTypes = new ArrayList<>();
			traitTypes.add(traitType);
			return charms.hasLearnedThresholdCharmsOfTrait(traitTypes, null, 1, 1);
		}
		
		@Override
		public void charmLearned(Charm charm) {
			CharmName excellencyName = new CharmName(getStringForExcellency(charm.getPrerequisites().getPrimaryTraitType().type));
			try {
				Charm excellency = charms.getCharmById(excellencyName);
				if (!charms.isLearned(excellency) &&
						hasOtherCharmsOfTrait(charm.getPrerequisites().getPrimaryTraitType().type)) {
					charms.getLearningModel().learnCharm(excellency, false);
				} 
			} catch (IllegalArgumentException e) {
				// corresponding excellency does not exist for this character
			}
		}

		@Override
		public void charmForgotten(Charm charm) {
			CharmName excellencyName = new CharmName(getStringForExcellency(charm.getPrerequisites().getPrimaryTraitType().type));
			try {
				Charm excellency = charms.getCharmById(excellencyName);
				if (charms.isLearned(excellency) &&
						!hasOtherCharmsOfTrait(charm.getPrerequisites().getPrimaryTraitType().type)) {
					charms.getLearningModel().forgetCharm(excellency, false);
				}
			} catch (IllegalArgumentException e) {
				// corresponding excellency does not exist for this character
			}
		}

		@Override
		public void charmNotLearnable(Charm charm) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void charmNotForgettable(Charm charm) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void recalculateRequested() {
			// TODO Auto-generated method stub
			
		}
	}
}
