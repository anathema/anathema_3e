package net.sf.anathema.hero.martial.display;

import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.martial.model.MartialArtsModel;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.view.trait.OptionalTraitsView;

@RegisteredInitializer(HeroModelGroup.Mundane)
@Weight(weight = 200)
public class MartialArtsInitializer implements HeroModelInitializer {
  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public MartialArtsInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String header = environment.getResources().getString("CardView.MartialArtsConfiguration.Title");
    OptionalTraitsView view = sectionView.addView(header, OptionalTraitsView.class);
    MartialArtsModel martialArtsModel = MartialArtsModelFetcher.fetch(hero);
    new MartialArtsPresenter(martialArtsModel, environment.getResources(), view).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    //TODO (Urs): Martial Arts for Mortals
    return CharmsModelFetcher.fetch(hero) != null;
  }
}