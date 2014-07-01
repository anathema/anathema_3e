package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.options.CharmOptionCheck;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.MARTIAL_ARTS;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.getCategory;

public class CharmProviderImpl implements CharmProvider {

  private final Map<CategoryReference, ISpecialCharm[]> specialCharmsByCategory = new HashMap<>();
  private final Map<CategoryReference, Charm[]> charmsByCategory = new HashMap<>();

  public CharmProviderImpl(CharmCache cache) {
    for (Identifier type : cache.getCharmTypes()) {
      CategoryReference categoryReference = getCategory(type);
      specialCharmsByCategory.put(categoryReference, cache.getSpecialCharmData(type));
      charmsByCategory.put(categoryReference, cache.getCharms(type));
    }
    CategoryReference martialArtsReference = getCategory(MARTIAL_ARTS);
    charmsByCategory.put(martialArtsReference, cache.getCharms(MARTIAL_ARTS));
  }

  @Override
  public Charm[] getCharms(CategoryReference category) {
    if (!charmsByCategory.containsKey(category)) {
      return new Charm[0];
    }
    return charmsByCategory.get(category);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(CharmOptionCheck check, CharmIdMap map, CategoryReference preferredCategory) {
    List<ISpecialCharm> relevantCharms = new ArrayList<>();
    ISpecialCharm[] allSpecialCharms = getAllSpecialCharms(preferredCategory);
    for (ISpecialCharm specialCharm : allSpecialCharms) {
      Charm charm = map.getCharmById(specialCharm.getCharmId());
      if (charm != null && check.isValidOptionForHeroType(charm)) {
        relevantCharms.add(specialCharm);
      }
    }
    return relevantCharms.toArray(new ISpecialCharm[relevantCharms.size()]);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(CategoryReference categoryReference) {
     ISpecialCharm[] specialCharms = specialCharmsByCategory.get(categoryReference);
    if (specialCharms == null) {
      specialCharms = new ISpecialCharm[0];
    }
    return specialCharms;
  }

  private ISpecialCharm[] getAllSpecialCharms(CategoryReference preferredCategory) {
    SpecialCharmSet set = new SpecialCharmSet();
    for (CategoryReference type : specialCharmsByCategory.keySet()) {
      set.add(getSpecialCharms(type));
    }
    for (ISpecialCharm preferredCharm : getSpecialCharms(preferredCategory)) {
      set.add(preferredCharm);
    }
    return set.toArray(new ISpecialCharm[set.size()]);
  }
}