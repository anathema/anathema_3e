package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.template.CharmTemplate;

public interface CharmGenerator {
	public void generateCharmForTemplate(CategoryReference category,
  		TreeName tree, String name, CharmTemplate charmTemplate);
	
	public CharmTemplate getBaseTemplate(String id);
}
