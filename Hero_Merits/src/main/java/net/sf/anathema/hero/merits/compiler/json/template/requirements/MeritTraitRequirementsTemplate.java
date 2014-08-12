package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;
import net.sf.anathema.hero.merits.model.requirements.MeritTraitRequirement;

public class MeritTraitRequirementsTemplate implements MeritRequirementsTemplate {
	public static final String jsonLabel = "trait";
	
	public String trait;
	public int rating;
	
	@Override
	public MeritRequirement generate() {
		return new MeritTraitRequirement(trait, rating);
	}
}
