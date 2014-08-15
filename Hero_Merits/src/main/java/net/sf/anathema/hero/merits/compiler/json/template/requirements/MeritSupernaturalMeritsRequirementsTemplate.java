package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;
import net.sf.anathema.hero.merits.model.requirements.MeritSupernaturalMeritsRequirement;

public class MeritSupernaturalMeritsRequirementsTemplate extends MeritRequirementsTemplate {
	
	public static String getJsonType() {
		return "supernaturalMerits";
	}

	@Override
	public MeritRequirement generate() {
		return new MeritSupernaturalMeritsRequirement();
	}
}
