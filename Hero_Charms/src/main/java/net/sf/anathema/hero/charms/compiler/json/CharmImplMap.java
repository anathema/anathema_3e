package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.template.prerequisite.CharmMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CharmImplMap implements CharmMap {
  private final Map<CharmName, CharmImpl> charmsByName = new HashMap<>();

  public void forEachCharm(Consumer<CharmImpl> consumer) {
    charmsByName.values().forEach(consumer);
  }

  public void put(CharmName charmName, CharmImpl charm) {
    charmsByName.put(charmName, charm);
  }

  public CharmImpl get(CharmName name) {
    return charmsByName.get(name);
  }
}
