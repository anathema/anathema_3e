package net.sf.anathema.hero.flaws.display.presenter;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.flaws.model.FlawsModel;
import net.sf.anathema.hero.flaws.model.FlawsModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.presenter.CategorizedOptionalPropertyPresenter;
import net.sf.anathema.library.view.property.OptionalPropertiesView;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.Background;

@RegisteredInitializer(Background)
@Weight(weight = 300)
public class FlawsInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public FlawsInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String viewName = environment.getResources().getString("AdditionalTemplateView.TabName.Flaws");
    OptionalPropertiesView view = sectionView.addView(viewName, OptionalPropertiesView.class);
    FlawsModel flawsModel = FlawsModelFetcher.fetch(hero);
    new CategorizedOptionalPropertyPresenter(hero, flawsModel, view, environment.getResources()).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return FlawsModelFetcher.fetch(hero) != null;
  }
}