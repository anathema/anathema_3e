package net.sf.anathema.hero.spells.template;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.CustomMeritOption;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("merit")
public class MeritLearnedInitiationTemplate implements CircleInitiationTemplate {
	public CircleType circle;
	public String meritId;
	
	@Override
	public boolean isInitiated(Hero hero) {
		return MeritsModelFetcher.fetch(hero).getMeritsOfOption(new CustomMeritOption(meritId)).size() > 0;
	}
}
