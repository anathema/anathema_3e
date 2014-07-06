package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.fx.dot.GroupedStatedDotsView;
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
    GroupedStatedDotsView abilityView = sectionView
            .addView(abilityHeader, GroupedStatedDotsView.class);
    new AbilitiesPresenter(hero, environment.getResources(), abilityView).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return AbilitiesModelFetcher.fetch(hero) != null;
  }
}