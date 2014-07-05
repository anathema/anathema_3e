package net.sf.anathema.hero.languages.display.presenter;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.languages.model.LanguagesModel;
import net.sf.anathema.hero.languages.model.LanguagesModelFetcher;
import net.sf.anathema.library.initialization.Weight;

@RegisteredInitializer(HeroModelGroup.NaturalTraits)
@Weight(weight = 400)
public class LanguagesInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public LanguagesInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String viewName = environment.getResources().getString("AdditionalTemplateView.TabName.Linguistics");
    LanguagesView view = sectionView.addView(viewName, LanguagesView.class);
    LanguagesModel languagesModel = LanguagesModelFetcher.fetch(hero);
    new LanguagesPresenter(languagesModel, view, environment.getResources()).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return LanguagesModelFetcher.fetch(hero) != null;
  }
}