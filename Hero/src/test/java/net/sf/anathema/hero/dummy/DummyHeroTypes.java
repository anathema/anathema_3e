package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.individual.splat.HeroType;

import java.util.Collections;
import java.util.Iterator;

public class DummyHeroTypes implements HeroTypes {
  private HeroType type;

  @Override
  public HeroType findById(String id) {
    if (type.getId().equals(id)) {
      return type;
    }
    throw new IllegalArgumentException("No type defined for id:" + id);
  }

  public void add(HeroType type) {
    this.type = type;
  }

  @Override
  public Iterator<HeroType> iterator() {
    return Collections.singletonList(type).iterator();
  }
}
