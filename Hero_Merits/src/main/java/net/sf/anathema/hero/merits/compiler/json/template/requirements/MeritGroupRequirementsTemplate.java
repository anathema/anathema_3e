package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritGroupRequirement;
import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;
import net.sf.anathema.platform.persistence.JsonType;

import java.util.ArrayList;
import java.util.List;

@JsonType("group")
public class MeritGroupRequirementsTemplate implements MeritRequirementsTemplate {
	
	public List<MeritRequirementsTemplate> requirements = new ArrayList<>();

	@Override
	public MeritRequirement generate() {
		List<MeritRequirement> generatedRequirements = new ArrayList<>();
		for (MeritRequirementsTemplate template : requirements) {
			generatedRequirements.add(template.generate());
		}
		return new MeritGroupRequirement(generatedRequirements);
	}
}
