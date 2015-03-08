package net.sf.anathema.magic.template.prerequisite;

import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.platform.persistence.JsonField;

@JsonField("type")
public interface CharmPrerequisiteTemplate {
	
	CharmPrerequisite generate(CharmMap charms);
}