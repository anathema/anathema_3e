package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.lib.util.Identifier;

import java.util.function.Predicate;

public class HasId <T extends Identifier> implements Predicate<T> {

  public static <S extends Identifier> HasId<S> HasId(String id) {
    return new HasId<S>(id);
  }

  private String id;

  public HasId(String id) {
    this.id = id;
  }

  @Override
  public boolean test(T t) {
    return t.getId().equals(id);
  }
}
