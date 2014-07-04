package net.sf.anathema.integration.concept;

import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.individual.model.Hero;

public class ConceptModelUtilities {

  public static void setCaste(Hero hero, String casteId) {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    CasteType casteType = concept.getCasteCollection().getById(casteId);
    concept.getCaste().setType(casteType);
  }
}
