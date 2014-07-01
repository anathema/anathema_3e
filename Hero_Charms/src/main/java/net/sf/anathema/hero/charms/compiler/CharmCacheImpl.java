package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmCacheImpl implements CharmCache {

  private MultiEntryMap<CategoryReference, Charm> charmSets = new MultiEntryMap<>();
  private Map<Identifier, List<SpecialCharmDto>> specialCharmsByType = new HashMap<>();
  private Map<String, Charm> charmsById = new HashMap<>();
  private CharmProvider charmProvider;
  private ReflectionSpecialCharmBuilder specialCharmBuilder;

  public CharmCacheImpl(ReflectionSpecialCharmBuilder builder) {
    this.specialCharmBuilder = builder;
  }

  @Override
  public Charm getCharmById(String charmId) {
    return charmsById.get(charmId);
  }

  @Override
  public Charm[] getCharms(CategoryReference type) {
    List<Charm> charmList = charmSets.get(type);
    return charmList.toArray(new Charm[charmList.size()]);
  }

  @Override
  public CharmProvider getCharmProvider() {
    if (charmProvider == null) {
      charmProvider = new CharmProviderImpl(this);
    }
    return charmProvider;
  }

  public void addCharm(CategoryReference type, Charm charm) {
    charmSets.replace(type, charm, charm);
    charmsById.put(charm.getMagicName().text, charm);
    if (charmProvider != null) {
      throw new IllegalStateException("Charms worked before compilation is complete.");
    }
  }

  public boolean isEmpty() {
    return charmSets.keySet().isEmpty();
  }

  public Iterable<Charm> getCharms() {
    List<Charm> allCharms = new ArrayList<>();
    for (CategoryReference type : charmSets.keySet()) {
      for (Charm charm : charmSets.get(type)) {
        allCharms.add(charm);
      }
    }
    return allCharms;
  }

  private List<SpecialCharmDto> getSpecialCharmList(Identifier type) {
    Map<Identifier, List<SpecialCharmDto>> map = specialCharmsByType;
    type = new SimpleIdentifier(type.getId());
    List<SpecialCharmDto> list = map.get(type);
    if (list == null) {
      list = new ArrayList<>();
      map.put(type, list);
    }
    return list;
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(CategoryReference type) {
    List<ISpecialCharm> specialCharms = new ArrayList<>();
    for (SpecialCharmDto dto : getSpecialCharmList(type)) {
      specialCharms.add(specialCharmBuilder.readCharm(dto));
    }
    return specialCharms.toArray(new ISpecialCharm[specialCharms.size()]);
  }

  public void addSpecialCharmData(Identifier type, List<SpecialCharmDto> data) {
    if (data == null) {
      return;
    }
    List<SpecialCharmDto> list = getSpecialCharmList(type);
    list.addAll(data);
  }

  @Override
  public List<CategoryReference> getAllCategories() {
    return new ArrayList(charmSets.keySet());
  }
}