package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.library.collection.MultiEntryMap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class CharmCacheImpl implements CharmCache {

  private MultiEntryMap<CategoryReference, Charm> charmsByCategory = new MultiEntryMap<>();
  private Map<CategoryReference, List<CharmSpecialLearning>> specialCharmsByCategory = new HashMap<>();
  private Map<CharmName, Charm> charmsById = new HashMap<>();
  private Multimap<CharmName, CharmSpecialMechanic> mechanics = ArrayListMultimap.create();

  @Override
  public Charm getCharmById(CharmName charmName) {
    return charmsById.get(charmName);
  }

  @Override
  public List<Charm> getCharms(CategoryReference type) {
    if (!charmsByCategory.containsKey(type)) {
      return Collections.emptyList();
    }
    List<Charm> charmList = charmsByCategory.get(type);
    return new ArrayList<>(charmList);
  }

  @Override
  public List<CategoryReference> getAllCategories() {
    return new ArrayList<>(charmsByCategory.keySet());
  }

  @Override
  public List<CharmSpecialLearning> getSpecialLearningCharms(CategoryReference type) {
    if (specialCharmsByCategory.containsKey(type)) {
      List<CharmSpecialLearning> specials = specialCharmsByCategory.get(type);
      return new ArrayList<>(specials);
    }
    return Collections.emptyList();
  }

  public Iterable<Charm> getCharms() {
    List<Charm> allCharms = new ArrayList<>();
    for (CategoryReference type : charmsByCategory.keySet()) {
      for (Charm charm : charmsByCategory.get(type)) {
        allCharms.add(charm);
      }
    }
    return allCharms;
  }

  public void addCharm(Charm charm) {
    charmsByCategory.replace(charm.getTreeReference().category, charm, charm);
    charmsById.put(charm.getName(), charm);
  }

  public void addSpecialLearning(CategoryReference type, List<CharmSpecialLearning> specialCharms) {
    if (specialCharms == null) {
      return;
    }
    List<CharmSpecialLearning> cachedList = getCachedSpecialLearningCharmList(type);
    cachedList.addAll(specialCharms);
  }
  
  public void addSpecialMechanics(String charmName, Collection<CharmSpecialMechanic> mechanics) {
    this.mechanics.putAll(new CharmName(charmName), mechanics);
  }

  private List<CharmSpecialLearning> getCachedSpecialLearningCharmList(CategoryReference type) {
    if (!specialCharmsByCategory.containsKey(type)) {
      specialCharmsByCategory.put(type, new ArrayList<>());
    }
    return specialCharmsByCategory.get(type);
  }

  public void addSpecialLearning(CharmSpecialLearning specialCharm) {
    Charm charm = getCharmById(specialCharm.getCharmName());
    addSpecialLearning(charm.getTreeReference().category, singletonList(specialCharm));
  }

	@Override
	public Collection<CharmSpecialMechanic> getSpecialMechanicsForCharm(CharmName name) {
		return mechanics.get(name);
	}

	@Override
	public List<Charm> getCharmsWithSpecialMechanics() {
		return mechanics.keySet().stream().collect(Collectors.mapping(key -> getCharmById(key), Collectors.toList()));
	}
}