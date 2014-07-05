package net.sf.anathema.points.display.experience;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;

@RegisteredInitializer(HeroModelGroup.Miscellaneous)
public class ExperiencePointInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public ExperiencePointInitializer(HeroEnvironment heroEnvironment) {
    this.environment = heroEnvironment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    new ExperiencePointPresenter(environment.getResources(), hero).initPresentation(sectionView);
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return true;
  }
}