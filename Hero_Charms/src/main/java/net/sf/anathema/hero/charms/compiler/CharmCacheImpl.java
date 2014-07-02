package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.lib.collection.MultiEntryMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmCacheImpl implements CharmCache {

  private MultiEntryMap<CategoryReference, CharmImpl> charmsByCategory = new MultiEntryMap<>();
  private Map<CategoryReference, List<ISpecialCharm>> specialCharmsByCategory = new HashMap<>();
  private Map<CharmName, CharmImpl> charmsById = new HashMap<>();
  private ReflectionSpecialCharmBuilder specialCharmBuilder;

  public CharmCacheImpl(ReflectionSpecialCharmBuilder builder) {
    this.specialCharmBuilder = builder;
  }

  @Override
  public Charm getCharmById(CharmName charmName) {
    return charmsById.get(charmName);
  }

  @Override
  public Charm[] getCharms(CategoryReference type) {
    if (!charmsByCategory.containsKey(type)) {
      return new Charm[0];
    }
    List<CharmImpl> charmList = charmsByCategory.get(type);
    return charmList.toArray(new Charm[charmList.size()]);
  }

  @Override
  public List<CategoryReference> getAllCategories() {
    return new ArrayList<>(charmsByCategory.keySet());
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(CategoryReference type) {
    if (specialCharmsByCategory.containsKey(type)) {
      List<ISpecialCharm> specials = specialCharmsByCategory.get(type);
      return specials.toArray(new ISpecialCharm[specials.size()]);
    }
    return new ISpecialCharm[0];
  }

  public void extractParents() {
    for (CharmImpl charm : charmsById.values()) {
      charm.extractParentCharms(charmsById);
    }
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

  public void addCharm(CategoryReference type, CharmImpl charm) {
    charmsByCategory.replace(type, charm, charm);
    charmsById.put(charm.getName(), charm);
  }

  public void addSpecialCharmData(CategoryReference type, List<SpecialCharmDto> data) {
    if (data == null) {
      return;
    }
    List<ISpecialCharm> specialCharms = addAndGetSpecialCharmList(type);
    for (SpecialCharmDto dto : data) {
      specialCharms.add(specialCharmBuilder.readCharm(dto));
    }
  }

  private List<ISpecialCharm> addAndGetSpecialCharmList(CategoryReference type) {
    if (!specialCharmsByCategory.containsKey(type)) {
      specialCharmsByCategory.put(type, new ArrayList<>());
    }
    return specialCharmsByCategory.get(type);
  }
}