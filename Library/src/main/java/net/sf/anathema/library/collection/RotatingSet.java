package net.sf.anathema.library.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class RotatingSet<E> implements Set<E> {

  private final ArrayList<E> list = new ArrayList<>();
  private final int maximalSize;

  public RotatingSet(int maximalSize) {
    this.maximalSize = maximalSize;
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return list.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }

  @Override
  public Object[] toArray() {
    return list.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return list.toArray(a);
  }

  @Override
  public boolean add(E e) {
    if (list.contains(e)) {
      return false;
    }
    if (size() >= maximalSize) {
      list.remove(0);
    }
    return list.add(e);
  }

  @Override
  public boolean remove(Object o) {
    return list.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return list.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    if (list.containsAll(c)) {
      return false;
    }
    for (E e : c) {
      add(e);
    }
    return true;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return list.retainAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return list.removeAll(c);
  }

  @Override
  public void clear() {
    list.clear();
  }
}
