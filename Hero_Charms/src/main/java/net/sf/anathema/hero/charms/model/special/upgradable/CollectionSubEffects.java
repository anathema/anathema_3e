package net.sf.anathema.hero.charms.model.special.upgradable;

import net.sf.anathema.hero.charms.model.special.subeffects.SubEffect;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionSubEffects implements SubEffects {

  private final List<SubEffect> effects = new ArrayList<>();

  public void add(SubEffect effect) {
    effects.add(effect);
  }

  @Override
  public SubEffect[] getEffects() {
    return effects.toArray(new SubEffect[effects.size()]);
  }

  @Override
  public SubEffect getById(final String id) {
    return effects.stream().filter(input -> input.getId().equals(id)).findFirst().get();
  }

  @Override
  public Iterator<SubEffect> iterator() {
    return effects.iterator();
  }
}