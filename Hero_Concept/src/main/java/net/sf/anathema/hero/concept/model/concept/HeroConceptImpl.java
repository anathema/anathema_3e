package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.AnnounceChangeListener;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;

import static net.sf.anathema.hero.concept.model.concept.ConceptChange.FLAVOR_CASTE;

public class HeroConceptImpl implements HeroConcept, HeroModel {

  private CasteModelImpl casteModel;

  public HeroConceptImpl(CasteModelImpl casteModel) {
    this.casteModel = casteModel;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    casteModel.getSelection().addChangeListener(new AnnounceChangeListener(announcer, FLAVOR_CASTE));
  }

  @Override
  public CasteSelection getCaste() {
    return casteModel.getSelection();
  }

  @Override
  public CasteCollection getCasteCollection() {
    return casteModel.getCollection();
  }
}