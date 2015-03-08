package net.sf.anathema.magic.template.prerequisite;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.magic.data.prerequisite.SpecificGroupCharmPrerequisite;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.platform.persistence.JsonType;

import java.util.ArrayList;
import java.util.List;

@JsonType("charmsFromGroup")
public class SpecificGroupCharmPrerequisiteTemplate implements CharmPrerequisiteTemplate {

	public List<String> options = new ArrayList<>();
	public int threshold = 1;
	
	@Override
	public CharmPrerequisite generate(CharmMap charms) {
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
