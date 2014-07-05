package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.display.fx.dot.GroupedFavorableDotConfigurationView;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 100)
public class AbilitiesInitializer implements HeroModelInitializer {
  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public AbilitiesInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String abilityHeader = environment.getResources().getString("CardView.AbilityConfiguration.Title");
    GroupedFavorableDotConfigurationView abilityView = sectionView
            .addView(abilityHeader, GroupedFavorableDotConfigurationView.class);
    new AbilitiesPresenter(hero, environment.getResources(), abilityView).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return AbilityModelFetcher.fetch(hero) != null;
  }
}