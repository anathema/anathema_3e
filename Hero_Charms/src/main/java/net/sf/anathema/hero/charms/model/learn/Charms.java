package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Charms implements Iterable<Charm> {
  public static Charms unique() {
    return new Charms(new HashSet<>());
  }

  public static Charms copyOf(Collection<Charm> charms) {
    return new Charms(new ArrayList<>(charms));
  }

  private final Collection<Charm> charms;

  public Charms() {
    this(new ArrayList<>());
  }

  public Charms(Collection<Charm> charms) {
    this.charms = charms;
  }

  public void add(Charm charm) {
    this.charms.add(charm);
  }

  public void addAll(Collection<Charm> charms) {
    this.charms.addAll(charms);
  }

  public void adopt(Charms charms) {
    this.charms.addAll(charms.charms);
  }

  public void removeAll(Charms charms) {
    this.charms.removeAll(charms.charms);
  }

  @Override
  public Iterator<Charm> iterator() {
    return charms.iterator();
  }

  public Charms applySort(Comparator<Charm> comparator) {
    ArrayList<Charm> copy = new ArrayList<>(charms);
    copy.sort(comparator);
    return new Charms(copy);
  }

  public Collection<? extends Charm> asList() {
    return new ArrayList<>(charms);
  }

  public Charms applyFilter(Predicate<Charm> filter) {
    List<Charm> filteredCharms = charms.stream().filter(filter).collect(Collectors.toList());
    return new Charms(filteredCharms);
  }

  public boolean hasCharms() {
    return !charms.isEmpty();
  }
}
