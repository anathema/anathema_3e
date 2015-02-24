package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpecialCharmSet {

  private final List<CharmSpecialLearning> list = new ArrayList<>();

  public void add(CharmSpecialLearning identificate) {
    for (CharmSpecialLearning existing : new ArrayList<>(list)) {
      if (existing.getCharmName().equals(identificate.getCharmName())) {
        list.remove(existing);
      }
    }
    list.add(identificate);
  }

  public CharmSpecialLearning[] toArray(CharmSpecialLearning[] array) {
    return list.toArray(array);
  }

  public int size() {
    return list.size();
  }

  public void addAll(Collection<CharmSpecialLearning> identificates) {
    for (CharmSpecialLearning identificate : identificates) {
      add(identificate);
    }
  }
}
