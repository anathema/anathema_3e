package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.magic.attribute.MagicAttribute;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.CharmParent;
import net.sf.anathema.hero.magic.charm.UnlinkedCharmMap;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.PrerequisiteProcessor;
import net.sf.anathema.lib.collection.MultiEntryMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static net.sf.anathema.hero.magic.charm.prerequisite.ProcessProcessor.process;

public class UnlinkedCharms implements UnlinkedCharmMap {

  private Map<CharmName, CharmImpl> charmsByName = new HashMap<>();
  private MultiEntryMap<CategoryReference, CharmImpl> charmsByCategory = new MultiEntryMap<>();
  private MultiEntryMap<CategoryReference, ISpecialCharm> specials = new MultiEntryMap<>();

  public Charm[] getCharms(CategoryReference category) {
    if (!charmsByCategory.containsKey(category)) {
      return new Charm[0];
    }
    List<CharmImpl> charmList = charmsByCategory.get(category);
    return charmList.toArray(new Charm[charmList.size()]);
  }

  public void addSpecialCharmData(CategoryReference category, List<ISpecialCharm> specialCharms) {
    for (ISpecialCharm special : specialCharms) {
      this.specials.replace(category, special, special);
    }
  }

  public void addCharm(CategoryReference category, CharmImpl charm) {
    charmsByCategory.add(category, charm, charm);
    charmsByName.put(charm.getName(), charm);
  }

  public void forEachCharm(Consumer<? super CharmImpl> action) {
    charmsByName.values().forEach(action);
  }

  public CharmImpl getCharmById(CharmName charmId) {
    return charmsByName.get(charmId);
  }

  public ExtensibleDataSet createCharmCache() {
    CharmCacheImpl cache = new CharmCacheImpl();
    forEachCharm(this::linkWithParentCharms);
    forEachCharm(cache::addCharm);
    specials.forEachKey(reference -> cache.addSpecial(reference, specials.get(reference)));
    return cache;
  }

  private void linkWithParentCharms(CharmImpl charm) {
    charm.getPrerequisites().forEachCharmPrerequisite(prerequisite -> prerequisite.link(this));
    charm.getPrerequisites().forEachCharmPrerequisite(process(new PrerequisiteProcessor() {
      @Override
      public void requiresMagicAttributes(MagicAttribute attribute, int count) {

      }

      @Override
      public void requiresCharm(Charm prerequisite) {
        ((CharmParent) prerequisite).addChild(charm);
      }

      @Override
      public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
        for (Charm prerequisite : prerequisites) {
          if (prerequisite instanceof CharmParent) {
            ((CharmParent) prerequisite).addChild(charm);
          }
        }
      }
    }));
    for (CharmPrerequisite prerequisite : charm.getPrerequisites().getCharmPrerequisites()) {
      prerequisite.link(this);
    }
  }

  @Override
  public CharmImpl get(CharmName charmName) {
    return charmsByName.get(charmName);
  }
}
