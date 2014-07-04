package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.herotype.solar.model.SolarType;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveLimitBreakModel;
import net.sf.anathema.herotype.solar.model.curse.GreatCurseFetcher;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModelImpl;

@RegisteredInitializer(HeroModelGroup.SpiritualTraits)
@Weight(weight = 200)
public class SolarVirtueFlawInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public SolarVirtueFlawInitializer(ApplicationModelImpl model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String viewName = environment.getString("AdditionalTemplateView.TabName.SolarVirtueFlaw");
    ConfigurableCharacterView view = sectionView.addView(viewName, ConfigurableCharacterView.class);
    DescriptiveLimitBreakModel virtueFlawModel = (DescriptiveLimitBreakModel) GreatCurseFetcher.fetch(hero);
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(environment, view, virtueFlawModel);
    presenter.initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return GreatCurseFetcher.fetch(hero) != null && hero.getSplat().getTemplateType().getCharacterType().getId().equals(SolarType.ID);
  }
}