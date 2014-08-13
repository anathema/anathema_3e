package net.sf.anathema.hero.merits.model.requirements;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritCategory;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;

public class MeritSupernaturalMeritsRequirement implements MeritRequirement {

	@Override
	public boolean isSatisfied(Hero hero) {
		MeritsModel merits = MeritsModelFetcher.fetch(hero);
		for (Merit merit : merits.getMerits()) {
			if (merit.getBaseOption().getType() == MeritCategory.Supernatural) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> getContingentTraitTypes() {
		return new ArrayList<String>();
	}

}
