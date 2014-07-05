package net.sf.anathema.hero.concept.display.description;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.elsewhere.concept.HeroConcept;
import net.sf.anathema.hero.elsewhere.concept.HeroConceptFetcher;
import net.sf.anathema.hero.elsewhere.description.HeroDescription;
import net.sf.anathema.hero.elsewhere.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.view.ConfigurableCharacterView;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.Outline;

@RegisteredInitializer(Outline)
@Weight(weight = 0)
public class DescriptionInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public DescriptionInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String descriptionHeader = environment.getResources().getString("CardView.CharacterDescription.Title");
    ConfigurableCharacterView descriptionView =
            sectionView.addView(descriptionHeader, ConfigurableCharacterView.class);
    DescriptionDetails descriptionDetails = createDescriptionDetails(hero);
    new DescriptionPresenter(descriptionDetails, environment, descriptionView).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return HeroDescriptionFetcher.fetch(hero) != null && HeroConceptFetcher.fetch(hero) != null;
  }

  private DescriptionDetails createDescriptionDetails(Hero hero) {
    HeroDescription characterDescription = HeroDescriptionFetcher.fetch(hero);
    HeroConcept heroConcept = HeroConceptFetcher.fetch(hero);
    boolean isExalt = hero.getSplat().getTemplateType().getCharacterType().isExaltType();
    return new DescriptionDetails(characterDescription, heroConcept, isExalt);
  }
}