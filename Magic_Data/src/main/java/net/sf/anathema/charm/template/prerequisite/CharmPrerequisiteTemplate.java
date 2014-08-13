package net.sf.anathema.charm.template.prerequisite;

import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;

public abstract class CharmPrerequisiteTemplate {
	
	public static final String getJsonField() {
		return "type";
	}
	
	public abstract CharmPrerequisite generate();
}
