package net.sf.anathema.points.display.experience;

import net.sf.anathema.hero.elsewhere.experience.ExperienceModel;
import net.sf.anathema.hero.elsewhere.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.points.model.PointModelFetcher;

public class ExperiencePointPresenter {

  private Resources resources;
  private Hero hero;

  public ExperiencePointPresenter(Resources resources, Hero hero) {
    this.resources = resources;
    this.hero = hero;
  }

  public void initPresentation(final SectionView section) {
    final ExperienceModel experienceModel = ExperienceModelFetcher.fetch(hero);
    initExperiencePointPresentation(experienceModel.isExperienced(), section);
    experienceModel.addStateChangeListener(() -> initExperiencePointPresentation(experienceModel.isExperienced(), section));
  }

  private void initExperiencePointPresentation(boolean experienced, SectionView section) {
    if (experienced) {
      String header = resources.getString("CardView.ExperienceConfiguration.Title");
      ExperienceView experienceView = section.addView(header, ExperienceView.class);
      new ExperienceConfigurationPresenter(resources, PointModelFetcher.fetch(hero).getExperiencePoints(), experienceView)
              .initPresentation();
    }
  }
}