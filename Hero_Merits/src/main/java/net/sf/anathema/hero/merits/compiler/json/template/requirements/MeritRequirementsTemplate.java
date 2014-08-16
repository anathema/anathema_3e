package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.platform.persistence.JsonField;
import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;

@JsonField("requirementType")
public interface MeritRequirementsTemplate {
	
	MeritRequirement generate();
}
