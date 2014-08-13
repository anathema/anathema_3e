package net.sf.anathema.charm.template.prerequisite;

import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.reference.CharmName;

public abstract class CharmPrerequisiteTemplate {
	
	public static final String getJsonField() {
		return "type";
	}
	
	public abstract CharmPrerequisite generate(Map<CharmName, Charm> charms);
}
