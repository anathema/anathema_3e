package net.sf.anathema.hero.charms.compiler.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteProcessor;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.template.CharmListTemplate;
import net.sf.anathema.charm.template.CharmTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import static net.sf.anathema.charm.data.prerequisite.IsConcreteCharmPrerequisite.isConcreteCharmPrerequisite;

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
    charmList.forEach(abstractCharmList::put);
    templateList.forEach((name, template) -> {
      CharmImpl charm = charmList.get(name);
      template.prerequisites.stream().forEach(prerequisiteTemplate -> {
        CharmPrerequisite prerequisite = prerequisiteTemplate.generate(abstractCharmList);
        if (isConcreteCharmPrerequisite().test(prerequisite)) {
          prerequisite.process(new PrerequisiteProcessor() {

            @Override
            public void requiresMagicAttributes(MagicAttribute attribute,
                                                int count) {
            }

            @Override
            public void requiresMagicAttributesFromTree(TreeReference tree,
                                                        MagicAttribute attribute, int count) {
            }

            @Override
            public void requiresCharm(Charm prerequisite) {
              ((CharmImpl) prerequisite).addChild(charm);
            }

            @Override
            public void requiresCharmFromSelection(Charm[] prerequisites,
                                                   int threshold) {
              for (Charm prerequisite : prerequisites) {
                ((CharmImpl) prerequisite).addChild(charm);
              }
            }

            @Override
            public void requiresCharmsOfTraits(List<RequiredTraitType> traits, CategoryReference category,
                                               int threshold, int minimumEssence) {
            }

            @Override
            public void requiresCharmsOfAnyOneTrait(int threshold) {
            }
          });
        }
        charm.addCharmPrerequisite(prerequisite);
      });
    });
  }
}
