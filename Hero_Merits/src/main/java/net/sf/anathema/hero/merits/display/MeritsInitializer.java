package net.sf.anathema.hero.merits.display;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.library.initialization.Weight;

@RegisteredInitializer(HeroModelGroup.Miscellaneous)
@Weight(weight = 300)
public class MeritsInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public MeritsInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String viewName = environment.getResources().getString("AdditionalTemplateView.TabName.Merits");
    MeritsView view = sectionView.addView(viewName, MeritsView.class);
    MeritsModel meritsModel = MeritsModelFetcher.fetch(hero);
    new MeritsPresenter(meritsModel, view, environment.getResources()).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return MeritsModelFetcher.fetch(hero) != null;
  }
}