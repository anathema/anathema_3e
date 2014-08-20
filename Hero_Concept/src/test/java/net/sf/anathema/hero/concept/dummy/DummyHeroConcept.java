package net.sf.anathema.hero.concept.dummy;

import net.sf.anathema.hero.concept.model.concept.CasteCollection;
import net.sf.anathema.hero.concept.model.concept.CasteSelection;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.identifier.Identifier;

public class DummyHeroConcept implements HeroConcept {

  public NullCasteSelection caste = new NullCasteSelection();
  private NullCasteCollection casteCollection = new NullCasteCollection();

  @Override
  public CasteSelection getCaste() {
    return caste;
  }

  @Override
  public CasteCollection getCasteCollection() {
    return casteCollection;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
