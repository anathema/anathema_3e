package net.sf.anathema.hero.merits.display.presenter;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritCategory;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.presenter.CategorizedOptionalTraitPresenter;
import net.sf.anathema.library.view.OptionalTraitsView;

@RegisteredInitializer(HeroModelGroup.Background)
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
    OptionalTraitsView view = sectionView.addView(viewName, OptionalTraitsView.class);
    MeritsModel meritsModel = MeritsModelFetcher.fetch(hero);
    new CategorizedOptionalTraitPresenter<MeritCategory, MeritOption, Merit>
    	(meritsModel, view, environment.getResources(), MeritOption.MAX_MERIT_RATING).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return MeritsModelFetcher.fetch(hero) != null;
  }
}