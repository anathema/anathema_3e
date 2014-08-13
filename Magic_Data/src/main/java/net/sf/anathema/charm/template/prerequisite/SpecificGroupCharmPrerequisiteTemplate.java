package net.sf.anathema.charm.template.prerequisite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.SpecificGroupCharmPrerequisite;
import net.sf.anathema.charm.data.reference.CharmName;

public class SpecificGroupCharmPrerequisiteTemplate extends CharmPrerequisiteTemplate {

	public List<String> options = new ArrayList<>();
	public int threshold = 1;
	
	public static String getJsonType() {
		return "charmsFromGroup";
	}
	
	@Override
	public CharmPrerequisite generate(Map<CharmName, Charm> charms) {
		List<Charm> groupCharms = new ArrayList<>();
		for (String charmName : options) {
			Charm charm = charms.get(new CharmName(charmName));
			if (charm != null) {
				groupCharms.add(charm);
			}
		}
		return new SpecificGroupCharmPrerequisite(groupCharms, threshold);
	}
	
}
