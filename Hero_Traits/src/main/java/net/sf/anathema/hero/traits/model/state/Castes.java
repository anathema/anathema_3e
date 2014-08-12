package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.individual.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class Castes {

  private final Hero hero;
  private final List<CasteType> castes = new ArrayList<>();

  public Castes(Hero hero) {
    this.hero = hero;
  }

  public void add(CasteType type) {
    castes.add(type);
  }

  public boolean isCurrentCasteSupported() {
    CasteType currentCaste = HeroConceptFetcher.fetch(hero).getCaste().getType();
    return castes.contains(currentCaste);
  }
}
