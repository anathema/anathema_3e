package net.sf.anathema.hero.martial.display;

import net.sf.anathema.hero.abilities.display.AbilitiesPresenter;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.martial.model.MartialArtsModel;
import net.sf.anathema.library.fx.dot.GroupedStatedDotsView;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.view.property.OptionalPropertiesView;

@RegisteredInitializer(HeroModelGroup.Mundane)
@Weight(weight = 100)
public class MartialArtsInitializer implements HeroModelInitializer {
  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public MartialArtsInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String header = environment.getResources().getString("CardView.MartialArtsConfiguration.Title");
    OptionalPropertiesView view = sectionView.addView(header, OptionalPropertiesView.class);
    MartialArtsModel martialArtsModel = MartialArtsModelFetcher.fetch(hero);
    new MartialArtsPresenter(martialArtsModel, environment.getResources(), view).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    //TODO (Urs): Martial Arts for Mortals
    return CharmsModelFetcher.fetch(hero) != null;
  }
}