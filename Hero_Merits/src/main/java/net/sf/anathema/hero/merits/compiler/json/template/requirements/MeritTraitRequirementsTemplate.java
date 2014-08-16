package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;
import net.sf.anathema.hero.merits.model.requirements.MeritTraitRequirement;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("trait")
public class MeritTraitRequirementsTemplate implements MeritRequirementsTemplate {
	
	public String trait;
	public int rating;
	
	@Override
	public MeritRequirement generate() {
		return new MeritTraitRequirement(trait.replaceAll(" ", ""), rating);
	}
}
