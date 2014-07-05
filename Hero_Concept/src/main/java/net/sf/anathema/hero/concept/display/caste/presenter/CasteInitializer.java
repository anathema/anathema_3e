package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.Outline;

@RegisteredInitializer(Outline)
@Weight(weight = 100)
public class CasteInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public CasteInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String conceptHeader = environment.getResources().getString("CardView.CharacterConcept.Title");
    CasteView conceptView = sectionView.addView(conceptHeader, CasteView.class);
    new CastePresenter(hero, conceptView, environment.getResources()).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    return concept != null && !concept.getCasteCollection().isEmpty();
  }
}