package net.sf.anathema.hero.charms.compiler.special;

import java.util.List;
import java.util.Map;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.template.CharmListTemplate;
import net.sf.anathema.charm.template.CharmTemplate;
import net.sf.anathema.charm.template.prerequisite.SimpleCharmPrerequisiteTemplate;
import net.sf.anathema.charm.template.special.Tier;
import net.sf.anathema.hero.charms.compiler.json.CharmGenerator;

public class AdditionalCharmFactory {
	private final CharmListTemplate baseTemplate;
	private final CharmGenerator generator;
	private final CategoryReference category;
  private final TreeName tree;
	
	public static final int TRAIT_MAX = 5;
	
	public AdditionalCharmFactory(CharmGenerator generator, CharmListTemplate baseTemplate) {
		this.baseTemplate = baseTemplate;
		this.generator = generator;
		this.category = new CategoryReference(baseTemplate.category);
		this.tree = new TreeName(baseTemplate.tree);
	}
	
	public void generateCharms(String id, List<Tier> tiers) {
		for (int repurchase = 0; repurchase < tiers.size(); repurchase++) {
			CharmTemplate newTemplate = generator.getBaseTemplate(id);
			tiers.get(repurchase).requirements.forEach(requirement -> newTemplate.minimums.put(requirement.traitType, requirement.traitValue));
			String predecessor = id + (repurchase == 0 ? "" : ".x" + (repurchase + 1));
			newTemplate.prerequisites.clear();
			newTemplate.prerequisites.add(new SimpleCharmPrerequisiteTemplate(predecessor));
			generator.generateCharmForTemplate(category, tree, id + ".x" + (repurchase + 2), newTemplate);
		}		
	}
	
	public Map<String, Integer> getCurrentMinimums(String id) {
		return generator.getBaseTemplate(id).minimums;
	}
	
	
}
