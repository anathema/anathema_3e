package net.sf.anathema.hero.abilities.model;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Count<T> implements Consumer<T> {

  public int count = 0;
  private Predicate<T> predicate;

  public Count(Predicate<T> predicate) {
    this.predicate = predicate;
  }

  @Override
  public void accept(T t) {
    if (predicate.test(t)) {
      count++;
    }
  }
}
