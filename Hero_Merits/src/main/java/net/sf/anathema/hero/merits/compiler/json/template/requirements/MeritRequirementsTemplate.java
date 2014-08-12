package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;

public interface MeritRequirementsTemplate {
	public static final String jsonLabel = "requirementType";
	
	public MeritRequirement generate();
}
