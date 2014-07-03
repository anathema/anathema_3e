package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.IntimaciesModelFetcher;
import net.sf.anathema.hero.model.Hero;

@RegisteredInitializer(HeroModelGroup.SpiritualTraits)
@Weight(weight = 300)
public class IntimaciesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public IntimaciesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, final Environment environment) {
    String viewName = environment.getString("AdditionalTemplateView.TabName.Intimacies");
    IntimaciesView view = sectionView.addView(viewName, IntimaciesView.class);
    IntimaciesModel intimaciesModel = IntimaciesModelFetcher.fetch(hero);
    new IntimaciesPresenter(intimaciesModel, view, environment).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return IntimaciesModelFetcher.fetch(hero) != null;
  }
}