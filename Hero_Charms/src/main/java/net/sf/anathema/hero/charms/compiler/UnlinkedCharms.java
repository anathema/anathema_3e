package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.UnlinkedCharmMap;
import net.sf.anathema.lib.collection.MultiEntryMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class UnlinkedCharms implements UnlinkedCharmMap {

  private Map<CharmName, CharmImpl> charmsByName = new HashMap<>();
  private MultiEntryMap<CategoryReference, CharmImpl> charmsByCategory = new MultiEntryMap<>();
  private MultiEntryMap<CategoryReference, ISpecialCharm> specialCharmsByCategory = new MultiEntryMap<>();

  public Charm[] getCharms(CategoryReference category) {
    if (!charmsByCategory.containsKey(category)) {
      return new Charm[0];
    }
    List<CharmImpl> charmList = charmsByCategory.get(category);
    return charmList.toArray(new Charm[charmList.size()]);
  }

  public void addSpecialCharmData(CategoryReference category, List<ISpecialCharm> specialCharms) {
    for (ISpecialCharm special : specialCharms) {
      specialCharmsByCategory.replace(category, special, special);
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
    forEachCharm(charm -> charm.extractParentCharms(this));
    CharmCacheImpl charmCache = new CharmCacheImpl();
    forEachCharm(charm -> charmCache.addCharm(charm));
    for (CategoryReference reference : specialCharmsByCategory.keySet()) {
      charmCache.addSpecialCharmData(reference, specialCharmsByCategory.get(reference));
    }
    return charmCache;
  }

  @Override
  public CharmImpl get(CharmName charmName) {
    return charmsByName.get(charmName);
  }
}
