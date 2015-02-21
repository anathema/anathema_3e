package net.sf.anathema.charm.template.prerequisite;

import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.platform.persistence.JsonField;

@JsonField("type")
public interface CharmPrerequisiteTemplate {
	
	CharmPrerequisite generate(CharmMap charms);
}