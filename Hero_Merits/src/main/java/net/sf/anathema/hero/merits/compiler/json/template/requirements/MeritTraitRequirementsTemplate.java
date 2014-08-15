package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;
import net.sf.anathema.hero.merits.model.requirements.MeritTraitRequirement;

public class MeritTraitRequirementsTemplate extends MeritRequirementsTemplate {
	
	public static String getJsonType() {
		return "trait";
	}
	
	public String trait;
	public int rating;
	
	@Override
	public MeritRequirement generate() {
		return new MeritTraitRequirement(trait.replaceAll(" ", ""), rating);
	}
}
