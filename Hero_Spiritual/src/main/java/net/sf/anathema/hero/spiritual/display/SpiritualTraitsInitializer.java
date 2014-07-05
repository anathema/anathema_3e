package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModelFetcher;
import net.sf.anathema.library.initialization.Weight;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.SpiritualTraits;

@RegisteredInitializer(SpiritualTraits)
@Weight(weight = 0)
public class SpiritualTraitsInitializer implements HeroModelInitializer {
  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public SpiritualTraitsInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String header = new DefaultSpiritualTraitsViewProperties(environment.getResources()).getOverallHeader();
    SpiritualTraitsView view = sectionView.addView(header, SpiritualTraitsView.class);
    new BasicSpiritualTraitsPresenter(environment.getResources(), hero, view).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return SpiritualTraitModelFetcher.fetch(hero) != null;
  }
}
