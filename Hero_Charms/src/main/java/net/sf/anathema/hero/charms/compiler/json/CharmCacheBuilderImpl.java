package net.sf.anathema.hero.charms.compiler.json;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.template.CharmListTemplate;
import net.sf.anathema.charm.template.CharmTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;

public class CharmCacheBuilderImpl implements CharmCacheBuilder, CharmGenerator {

  private final Map<CharmName, CharmImpl> charmList = new HashMap<>();
  private final Map<CharmName, CharmTemplate> templateList = new HashMap<>();

  public void addTemplate(CharmListTemplate charmList) {
    addCharmSkeletons(charmList);
  }

  public CharmCacheImpl createCache() {
    linkCharms();
    CharmCacheImpl charmCache = new CharmCacheImpl();
    charmList.values().forEach(charmCache::addCharm);
    return charmCache;
  }

  private void addCharmSkeletons(CharmListTemplate listTemplate) {
    CategoryReference category = new CategoryReference(listTemplate.category);
    TreeName tree = new TreeName(listTemplate.tree);
    listTemplate.charms.forEach((name, charmTemplate) -> {
      generateCharmForTemplate(category, tree, name, charmTemplate);
    });
  }
  
  public void generateCharmForTemplate(CategoryReference category,
  		TreeName tree, String name, CharmTemplate charmTemplate) {
  	CharmName charmName = new CharmName(name);
    CharmImpl charm = new CharmImpl(category, tree, charmName, charmTemplate);
    charmList.put(charmName, charm);
    templateList.put(charmName, charmTemplate);
  }
  
  public CharmTemplate getBaseTemplate(String id) {
  	return templateList.get(new CharmName(id)).clone();
  }

  private void linkCharms() {
  	Map<CharmName, Charm> abstractCharmList = new HashMap<>();
  	charmList.forEach((name, charm) -> abstractCharmList.put(name, charm));
    templateList.forEach((name, template) -> {
      CharmImpl charm = charmList.get(name);
      template.prerequisites.stream().forEach(prerequisite -> {
    	  charm.addCharmPrerequisite(prerequisite.generate(abstractCharmList));
      });
    });
  }
}
