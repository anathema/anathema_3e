package net.sf.anathema.hero.spells.template;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.library.model.OptionalEntryReference;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("merit")
public class MeritLearnedInitiationTemplate implements CircleInitiationTemplate {
	public CircleType circle;
	public String meritId;
	
	@Override
	public boolean isInitiated(Hero hero) {
      MeritsModel model = MeritsModelFetcher.fetch(hero);
      OptionalEntryReference reference = new OptionalEntryReference(meritId);
      return model.hasMeritsMatchingReference(reference);
	}
}
