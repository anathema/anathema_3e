package net.sf.anathema.charm.template.prerequisite;

import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("charm")
public class SimpleCharmPrerequisiteTemplate implements CharmPrerequisiteTemplate {

	public String id;
	
	@Override
	public CharmPrerequisite generate(Map<CharmName, Charm> charms) {
		return new SimpleCharmPrerequisite(charms.get(new CharmName(id)));
	}

}
