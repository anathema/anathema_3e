package net.sf.anathema.hero.application.type;

import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.individual.splat.HeroType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExtensibleHeroTypes implements HeroTypes {

  private final List<HeroType> types = new ArrayList<>();

  public void add(HeroType type) {
    types.add(type);
  }
  
  @Override
  public HeroType findById(String id) {
    for (HeroType type : types) {
      if (type.getId().equals(id)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No type defined for id:" + id);
  }

  @Override
  public Iterator<HeroType> iterator() {
    return types.iterator();
  }
}