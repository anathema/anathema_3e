package net.sf.anathema.hero.charms.model.special.subeffects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ArraySubEffects implements SubEffects {
  private final List<SubEffect> subeffects = new ArrayList<>();

  public ArraySubEffects(SubEffect[] subeffects) {
    Collections.addAll(this.subeffects, subeffects);
  }

  @Override
  public List<SubEffect> getEffects() {
    return new ArrayList<>(subeffects);
  }

  @Override
  public SubEffect getById(final String id) {
    Stream<SubEffect> effects = subeffects.stream();
    return effects.filter(input -> input.getId().equals(id)).findFirst().orElse(null);
  }

  @Override
  public Iterator<SubEffect> iterator() {
    return subeffects.iterator();
  }
}