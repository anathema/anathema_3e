package net.sf.anathema.integration.concept;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.individual.model.Hero;

public class ConceptModelUtilities {

  public static void setCaste(Hero hero, String casteId) {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    CasteType casteType = concept.getCasteCollection().getById(casteId);
    concept.getCaste().setType(casteType);
  }
}
