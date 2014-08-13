package net.sf.anathema.charm.template.prerequisite;

import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.AnyOneTraitCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.reference.CharmName;

public class AnyOneTraitCharmPrerequisiteTemplate extends CharmPrerequisiteTemplate {

	public int threshold;
	
	public static String getJsonType() {
		return "anyOneTraitWithCountCharms";
	}

	@Override
	public CharmPrerequisite generate(Map<CharmName, Charm> charms) {
		return new AnyOneTraitCharmPrerequisite(threshold);
	}
}
