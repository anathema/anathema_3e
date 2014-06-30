package net.sf.anathema.points.display.experience;

import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;

@RegisteredInitializer(HeroModelGroup.Miscellaneous)
public class ExperiencePointInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public ExperiencePointInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    new ExperiencePointPresenter(environment, hero).initPresentation(sectionView);
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return true;
  }
}