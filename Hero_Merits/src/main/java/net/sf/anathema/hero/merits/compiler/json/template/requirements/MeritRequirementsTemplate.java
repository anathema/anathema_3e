package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;
import net.sf.anathema.platform.persistence.JsonField;

@JsonField("requirementType")
public interface MeritRequirementsTemplate {
	
	MeritRequirement generate();
}
