package net.sf.anathema.charm.template.prerequisite;

import net.sf.anathema.charm.data.prerequisite.AnyOneTraitCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("anyOneTraitWithCountCharms")
public class AnyOneTraitCharmPrerequisiteTemplate implements CharmPrerequisiteTemplate {

	public int threshold;
	
	@Override
	public CharmPrerequisite generate(CharmMap charms) {
		return new AnyOneTraitCharmPrerequisite(threshold);
	}
}
