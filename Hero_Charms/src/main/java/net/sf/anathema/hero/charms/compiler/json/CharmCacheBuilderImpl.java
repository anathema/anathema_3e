package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.magic.data.reference.TreeName;
import net.sf.anathema.magic.template.CharmListTemplate;
import net.sf.anathema.magic.template.CharmTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;

public class CharmCacheBuilderImpl implements CharmCacheBuilder, CharmGenerator {

  private final CharmImplMap charmMap = new CharmImplMap();
  private final TemplateMap templateMap = new TemplateMap();

  public void addTemplate(CharmListTemplate charmList) {
    addCharmSkeletons(charmList);
  }

  public CharmCacheImpl createCache() {
    linkCharms();
    CharmCacheImpl charmCache = new CharmCacheImpl();
    charmMap.forEachCharm(charmCache::addCharm);
    return charmCache;
  }

  private void addCharmSkeletons(CharmListTemplate listTemplate) {
    CategoryReference category = new CategoryReference(listTemplate.category);
    TreeName tree = new TreeName(listTemplate.tree);
    listTemplate.charms.forEach((name, charmTemplate) -> generateCharmForTemplate(category, tree, name, charmTemplate));
  }

  public void generateCharmForTemplate(CategoryReference category,
                                       TreeName tree, String name, CharmTemplate charmTemplate) {
    CharmName charmName = new CharmName(name);
    CharmImpl charm = new CharmImpl(category, tree, charmName, charmTemplate);
    charmMap.put(charmName, charm);
    templateMap.put(charmName, charmTemplate);
  }

  public CharmTemplate getBaseTemplate(String id) {
    return templateMap.getClonedTemplate(new CharmName(id));
  }

  private void linkCharms() {
    templateMap.forEach((name, template) -> {
      CharmImpl charm = charmMap.get(name);
      template.prerequisites.stream().forEach(prerequisiteTemplate -> {
        CharmPrerequisite prerequisite = prerequisiteTemplate.generate(charmMap);
        prerequisite.process(new LinkParentsToChild(charm));
        charm.addCharmPrerequisite(prerequisite);
      });
    });
  }

}