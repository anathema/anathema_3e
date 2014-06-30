package net.sf.anathema.points.display.overview;

import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.points.model.BonusPointManagement;
import net.sf.anathema.points.model.ExperiencePointManagement;
import net.sf.anathema.points.model.ExperiencePointManagementImpl;
import net.sf.anathema.points.display.overview.presenter.OverviewPresenter;
import net.sf.anathema.points.display.overview.view.OverviewContainer;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.points.model.PointModelFetcher;

import static net.sf.anathema.hero.display.HeroModelGroup.Miscellaneous;

@RegisteredInitializer(Miscellaneous)
public class OverviewInitializer implements HeroModelInitializer {
  private IApplicationModel applicationModel;

  @SuppressWarnings("UnusedParameters")
  public OverviewInitializer(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String header = "Overview";
    OverviewContainer container = sectionView.addView(header, OverviewContainer.class);
    initOverviewPresentation(hero, container, environment);
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return PointModelFetcher.fetch(hero) != null;
  }

  private void initOverviewPresentation(Hero hero, OverviewContainer container, Resources resources) {
    BonusPointManagement bonusPoints = PointModelFetcher.fetch(hero).getBonusPointManagement();
    ExperiencePointManagement experiencePoints = new ExperiencePointManagementImpl(hero);
    IMessaging messaging = applicationModel.getMessaging();
    OverviewPresenter presenter = new OverviewPresenter(resources, hero, container, bonusPoints, experiencePoints, messaging);
    presenter.initPresentation();
  }
}