package net.sf.anathema.charm.template.prerequisite;

import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.platform.persistence.JsonField;

@JsonField("type")
public interface CharmPrerequisiteTemplate {
	
	CharmPrerequisite generate(Map<CharmName, Charm> charms);
}