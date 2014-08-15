package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritMortalRequirements;
import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;

public class MeritMortalRequirementsTemplate extends MeritRequirementsTemplate {
	
	public static String getJsonType() {
		return "mortal";
	}

	@Override
	public MeritRequirement generate() {
		return new MeritMortalRequirements();
	}	
	
}
