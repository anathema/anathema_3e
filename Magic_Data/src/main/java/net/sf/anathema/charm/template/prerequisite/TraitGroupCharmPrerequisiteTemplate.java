package net.sf.anathema.charm.template.prerequisite;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;
import net.sf.anathema.charm.data.reference.CharmName;

public class TraitGroupCharmPrerequisiteTemplate extends CharmPrerequisiteTemplate {

	public List<String> traits;
	public int count;
	public int minimumEssence = 1;
	
	public static String getJsonType() {
		return "numberOfTraitCharms";
	}

	@Override
	public CharmPrerequisite generate(Map<CharmName, Charm> charms) {
		return new TraitGroupCharmPrerequisite((List<RequiredTraitType>)Lists.transform(traits, trait -> new RequiredTraitType(trait)),
				count, minimumEssence);
	}
}
