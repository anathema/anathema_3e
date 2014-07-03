package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.template.CharmListTemplate;
import net.sf.anathema.charm.template.CharmTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.charm.data.prerequisite.SimpleCharmPrerequisite;

import java.util.HashMap;
import java.util.Map;

public class CharmCacheBuilder {

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
      CharmName charmName = new CharmName(name);
      CharmImpl charm = new CharmImpl(category, tree, charmName, charmTemplate);
      charmList.put(charmName, charm);
      templateList.put(charmName, charmTemplate);
    });
  }

  private void linkCharms() {
    templateList.forEach((name, template) -> {
      CharmImpl charm = charmList.get(name);
      template.prerequisiteCharms.stream().forEach(nameString -> {
        CharmImpl parent = charmList.get(new CharmName(nameString));
        parent.addChild(charm);
        charm.addCharmPrerequisite(new SimpleCharmPrerequisite(parent));
      });
    });
  }
}
