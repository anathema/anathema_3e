package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.IntimaciesModelFetcher;
import net.sf.anathema.library.initialization.Weight;

@RegisteredInitializer(HeroModelGroup.SpiritualTraits)
@Weight(weight = 300)
public class IntimaciesInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public IntimaciesInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String viewName = environment.getResources().getString("AdditionalTemplateView.TabName.Intimacies");
    IntimaciesView view = sectionView.addView(viewName, IntimaciesView.class);
    IntimaciesModel intimaciesModel = IntimaciesModelFetcher.fetch(hero);
    new IntimaciesPresenter(intimaciesModel, view, environment.getResources()).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return IntimaciesModelFetcher.fetch(hero) != null;
  }
}