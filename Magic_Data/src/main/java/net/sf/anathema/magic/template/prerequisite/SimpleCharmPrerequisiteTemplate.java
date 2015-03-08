package net.sf.anathema.magic.template.prerequisite;

import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.magic.data.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("charm")
public class SimpleCharmPrerequisiteTemplate implements CharmPrerequisiteTemplate {

	public String id;
	
	public SimpleCharmPrerequisiteTemplate(String id) {
		this.id = id;
	}
	
	@Override
	public CharmPrerequisite generate(CharmMap charms) {
		return new SimpleCharmPrerequisite(charms.get(new CharmName(id)));
	}
}