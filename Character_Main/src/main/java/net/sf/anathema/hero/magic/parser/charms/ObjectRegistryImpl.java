package net.sf.anathema.hero.magic.parser.charms;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class ObjectRegistryImpl<E> implements ObjectRegistry<E> {

  private final Set<E> elements = new LinkedHashSet<>();

  @Override
  public void add(E... newElements) {
    Collections.addAll(elements, newElements);
  }

  @Override
  public final Collection<E> getAll() {
    return elements;
  }

  @Override
  public boolean isRegistered(Predicate<E> predicate) {
    return elements.stream().findAny().isPresent();
  }

  @Override
  public boolean isRegistered(E object) {
    return elements.contains(object);
  }

  @Override
  public E getFirst(Predicate<E> predicate) {
    Optional<E> first = elements.stream().filter(predicate).findFirst();
    return first.isPresent() ? first.get() : null;
  }
}