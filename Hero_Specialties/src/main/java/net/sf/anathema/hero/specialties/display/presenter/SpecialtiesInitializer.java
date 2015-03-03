package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.presenter.CategorizedOptionalPropertyPresenter;
import net.sf.anathema.library.view.property.OptionalPropertiesView;

@RegisteredInitializer(HeroModelGroup.Mundane)
@Weight(weight = 300)
public class SpecialtiesInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public SpecialtiesInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String viewName = environment.getResources().getString("AdditionalTemplateView.TabName.Specialties");
    OptionalPropertiesView view = sectionView.addView(viewName, OptionalPropertiesView.class);
    SpecialtiesModel specialtiesModel = SpecialtiesModelFetcher.fetch(hero);
    new CategorizedOptionalPropertyPresenter(hero, specialtiesModel, view, environment.getResources()).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return SpecialtiesModelFetcher.fetch(hero) != null;
  }
}