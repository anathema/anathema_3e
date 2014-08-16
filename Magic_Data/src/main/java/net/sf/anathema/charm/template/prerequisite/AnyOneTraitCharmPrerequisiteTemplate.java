package net.sf.anathema.charm.template.prerequisite;

import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.AnyOneTraitCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("anyOneTraitWithCountCharms")
public class AnyOneTraitCharmPrerequisiteTemplate implements CharmPrerequisiteTemplate {

	public int threshold;
	
	@Override
	public CharmPrerequisite generate(Map<CharmName, Charm> charms) {
		return new AnyOneTraitCharmPrerequisite(threshold);
	}
}
