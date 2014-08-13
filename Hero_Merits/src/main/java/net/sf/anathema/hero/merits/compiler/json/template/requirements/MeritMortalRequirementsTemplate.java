package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritMortalRequirements;
import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;

public class MeritMortalRequirementsTemplate implements MeritRequirementsTemplate {
	public static final String jsonLabel = "mortal";

	@Override
	public MeritRequirement generate() {
		return new MeritMortalRequirements();
	}	
	
}
