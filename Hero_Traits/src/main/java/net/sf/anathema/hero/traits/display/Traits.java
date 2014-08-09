package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitIterable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Traits implements TraitIterable {
  private final List<Trait> traits = new ArrayList<>();
  
  public Traits() {
    //nothing to do
  }

  public Traits(Trait trait) {
    traits.add(trait);
  }

  public Traits(Collection<Trait> traits) {
    this.traits.addAll(traits);
  }

  public void add(Trait trait) {
    traits.add(trait);
  }

  @Override
  public Iterator<Trait> iterator() {
    return traits.iterator();
  }
}