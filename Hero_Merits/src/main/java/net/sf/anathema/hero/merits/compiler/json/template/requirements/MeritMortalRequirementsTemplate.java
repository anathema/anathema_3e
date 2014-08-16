package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritMortalRequirements;
import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("mortal")
public class MeritMortalRequirementsTemplate implements MeritRequirementsTemplate {
	
	@Override
	public MeritRequirement generate() {
		return new MeritMortalRequirements();
	}	
}