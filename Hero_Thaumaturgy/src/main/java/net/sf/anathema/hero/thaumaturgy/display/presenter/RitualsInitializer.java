package net.sf.anathema.hero.thaumaturgy.display.presenter;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.thaumaturgy.display.presenter.RitualsPresenter;
import net.sf.anathema.hero.thaumaturgy.display.presenter.RitualsView;
import net.sf.anathema.hero.thaumaturgy.model.MeritsModelFetcher;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModel;
import net.sf.anathema.library.initialization.Weight;

@RegisteredInitializer(HeroModelGroup.Sorcery)
@Weight(weight = 300)
public class RitualsInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public RitualsInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String viewName = environment.getResources().getString("AdditionalTemplateView.TabName.Thaumaturgy");
    RitualsView view = sectionView.addView(viewName, RitualsView.class);
    ThaumaturgyModel meritsModel = MeritsModelFetcher.fetch(hero);
    new RitualsPresenter(meritsModel, view, environment.getResources()).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return MeritsModelFetcher.fetch(hero) != null;
  }
}