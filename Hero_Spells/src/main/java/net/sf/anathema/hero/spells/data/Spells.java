package net.sf.anathema.hero.spells.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Spells implements Iterable<Spell> {
  public static Spells singleSpell(Spell spell) {
    return from(spell);
  }

  public static Spells from(Spell... spells) {
    Spells spellSet = new Spells();
    for (Spell spell : spells) {
      spellSet.add(spell);
    }
    return spellSet;
  }

  public static Spells copyOf(Collection<Spell> spells) {
    Spells spellSet = new Spells();
    spellSet.addAll(spells);
    return spellSet;
  }

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
