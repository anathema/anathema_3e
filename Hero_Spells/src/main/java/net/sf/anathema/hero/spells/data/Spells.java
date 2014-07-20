package net.sf.anathema.hero.spells.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Spells implements Iterable<Spell>{
  private final List<Spell> list = new ArrayList<>();

  @Override
  public Iterator<Spell> iterator() {
    return list.iterator();
  }

  public void add(Spell spell) {
    this.list.add(spell);
  }

  public void addAll(Collection<Spell> spells) {
    list.addAll(spells);
  }

  public void adopt(Spells spells) {
    this.list.addAll(spells.list);
  }

  public void removeAll(Spells spells) {
    this.list.removeAll(spells.list);
  }

  public List<Spell> asList() {
    return new ArrayList<>(list);
  }
}
