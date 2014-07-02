package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.lib.util.Identifier;

import java.util.Collection;
import java.util.function.Predicate;

public interface ObjectRegistry<E> {

  void add(E... elements);

  Collection<E> getAll();

  boolean isRegistered(Predicate<E> predicate);

  boolean isRegistered(E object);

  E getFirst(Predicate<E> predicate);
}