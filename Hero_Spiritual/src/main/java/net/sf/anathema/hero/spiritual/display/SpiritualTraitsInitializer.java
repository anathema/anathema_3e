package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModelFetcher;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import static net.sf.anathema.hero.display.HeroModelGroup.SpiritualTraits;

@RegisteredInitializer(SpiritualTraits)
@Weight(weight = 0)
public class SpiritualTraitsInitializer implements HeroModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public SpiritualTraitsInitializer(ApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String header = new DefaultSpiritualTraitsViewProperties(environment).getOverallHeader();
    SpiritualTraitsView view = sectionView.addView(header, SpiritualTraitsView.class);
    new BasicSpiritualTraitsPresenter(environment, hero, view).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return SpiritualTraitModelFetcher.fetch(hero) != null;
  }
}
