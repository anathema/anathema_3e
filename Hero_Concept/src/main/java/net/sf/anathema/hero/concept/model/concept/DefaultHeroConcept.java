package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.hero.elsewhere.concept.CasteCollection;
import net.sf.anathema.hero.elsewhere.concept.CasteSelection;
import net.sf.anathema.hero.elsewhere.concept.ConceptChange;
import net.sf.anathema.hero.elsewhere.concept.HeroConcept;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.change.AnnounceChangeListener;
import net.sf.anathema.library.change.ChangeAnnouncer;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.IntegerModel;
import net.sf.anathema.library.model.IntegerModelImpl;

public class DefaultHeroConcept implements HeroConcept, HeroModel {

  private final IntegerModel age = new IntegerModelImpl(0);
  private DefaultCasteModel casteModel;

  public DefaultHeroConcept(DefaultCasteModel casteModel) {
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
    casteModel.getSelection().addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_CASTE));
    age.addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_AGE));
  }

  @Override
  public CasteSelection getCaste() {
    return casteModel.getSelection();
  }

  @Override
  public CasteCollection getCasteCollection() {
    return casteModel.getCollection();
  }

  @Override
  public IntegerModel getAge() {
    return age;
  }
}