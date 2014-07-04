package net.sf.anathema.hero.charms.model.special.subeffects;

import com.google.common.collect.Lists;

import java.util.Iterator;

import static net.sf.anathema.library.lang.ArrayUtilities.getFirst;

public class ArraySubEffects implements SubEffects {
  private final SubEffect[] subeffects;

  public ArraySubEffects(SubEffect[] subeffects) {
    this.subeffects = subeffects;
  }

  @Override
  public SubEffect[] getEffects() {
    return subeffects;
  }

  @Override
  public SubEffect getById(final String id) {
    return getFirst(subeffects, input -> input.getId().equals(id));
  }

  @Override
  public Iterator<SubEffect> iterator() {
    return Lists.newArrayList(subeffects).iterator();
  }
}