package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModelImpl;

@RegisteredInitializer(HeroModelGroup.NaturalTraits)
@Weight(weight = 300)
public class SpecialtiesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public SpecialtiesInitializer(ApplicationModelImpl model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String viewName = environment.getString("AdditionalTemplateView.TabName.Specialties");
    SpecialtiesConfigurationView view = sectionView.addView(viewName, SpecialtiesConfigurationView.class);
    SpecialtiesModel specialtiesModel = SpecialtiesModelFetcher.fetch(hero);
    new SpecialtiesConfigurationPresenter(hero, specialtiesModel, view, environment).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return SpecialtiesModelFetcher.fetch(hero) != null;
  }
}