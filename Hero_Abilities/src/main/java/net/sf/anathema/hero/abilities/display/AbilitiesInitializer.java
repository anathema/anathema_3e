package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.display.fx.traitview.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 100)
public class AbilitiesInitializer implements HeroModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public AbilitiesInitializer(ApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String abilityHeader = environment.getString("CardView.AbilityConfiguration.Title");
    GroupedFavorableTraitConfigurationView abilityView = sectionView
            .addView(abilityHeader, GroupedFavorableTraitConfigurationView.class);
    new AbilitiesPresenter(hero, environment, abilityView).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return AbilityModelFetcher.fetch(hero) != null;
  }
}