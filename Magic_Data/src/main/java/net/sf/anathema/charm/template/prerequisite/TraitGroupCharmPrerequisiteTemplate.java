package net.sf.anathema.charm.template.prerequisite;

import java.util.List;

import com.google.common.collect.Lists;

import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;

public class TraitGroupCharmPrerequisiteTemplate extends CharmPrerequisiteTemplate {

	public List<String> traits;
	public int count;
	public int minimumEssence = 1;
	
	public static String getJsonType() {
		return "numberOfTraitCharms";
	}

	@Override
	public CharmPrerequisite generate() {
		return new TraitGroupCharmPrerequisite((List<RequiredTraitType>)Lists.transform(traits, trait -> new RequiredTraitType(trait)),
				count, minimumEssence);
	}
}
