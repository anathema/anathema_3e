package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;

public abstract class MeritRequirementsTemplate {
	
	public static String getJsonField() {
		return "requirementType";
	}
	
	public abstract MeritRequirement generate();
}
