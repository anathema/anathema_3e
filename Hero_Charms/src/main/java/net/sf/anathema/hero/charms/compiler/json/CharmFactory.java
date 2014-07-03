package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.parser.template.CharmListTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;

import java.util.HashMap;
import java.util.Map;

public class CharmFactory {

  private final Map<CharmName, DefaultCharm> charmList = new HashMap<>();

  public void addTemplate(CharmListTemplate listTemplate) {
    addCharmSkeletons(listTemplate);
  }

  public CharmCache createCache() {
    linkCharms();
    CharmCacheImpl charmCache = new CharmCacheImpl();
    charmList.values().stream().forEach(charm -> charmCache.addCharm(charm));
    return charmCache;
  }

  private void addCharmSkeletons(CharmListTemplate listTemplate) {
    CategoryReference category = new CategoryReference(listTemplate.category);
    TreeName tree = new TreeName(listTemplate.treeName);
    listTemplate.charmTemplatesByName.forEach((name, charmTemplate) -> {
      CharmName charmName = new CharmName(name);
      DefaultCharm charm = new DefaultCharm(category, tree, charmName, charmTemplate);
      charmList.put(charmName, charm);
    });
  }

  private void linkCharms() {

  }
}
