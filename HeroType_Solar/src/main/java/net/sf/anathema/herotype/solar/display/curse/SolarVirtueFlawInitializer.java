package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.herotype.solar.model.SolarType;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveLimitBreakModel;
import net.sf.anathema.herotype.solar.model.curse.GreatCurseFetcher;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.view.ConfigurableCharacterView;

@RegisteredInitializer(HeroModelGroup.SpiritualTraits)
@Weight(weight = 200)
public class SolarVirtueFlawInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public SolarVirtueFlawInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String viewName = environment.getResources().getString("AdditionalTemplateView.TabName.SolarVirtueFlaw");
    ConfigurableCharacterView view = sectionView.addView(viewName, ConfigurableCharacterView.class);
    DescriptiveLimitBreakModel virtueFlawModel = (DescriptiveLimitBreakModel) GreatCurseFetcher.fetch(hero);
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(environment.getResources(), view, virtueFlawModel);
    presenter.initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return GreatCurseFetcher.fetch(hero) != null && hero.getSplat().getTemplateType().getCharacterType().getId().equals(SolarType.ID);
  }
}