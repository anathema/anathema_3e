package net.sf.anathema.points.display.overview.presenter;

import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.overview.OverviewCategory;
import net.sf.anathema.library.legality.LegalityColorProvider;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.LabelledAllotmentView;
import net.sf.anathema.library.view.StyledValueView;
import net.sf.anathema.platform.messaging.Messaging;
import net.sf.anathema.points.display.overview.view.CategorizedOverview;
import net.sf.anathema.points.model.ExperiencePointManagement;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.overview.IValueModel;

import java.util.ArrayList;
import java.util.List;

public class ExperiencedOverviewPresenter {

  private final ExperiencePointManagement management;
  private final CategorizedOverview view;
  private final Hero hero;
  private Messaging messaging;
  private final Resources resources;
  private final List<IOverviewSubPresenter> presenters = new ArrayList<>();

  private LabelledAllotmentView totalView;

  public ExperiencedOverviewPresenter(Resources resources, Hero hero, CategorizedOverview overview,
                                      ExperiencePointManagement experiencePoints, Messaging messaging) {
    this.resources = resources;
    this.hero = hero;
    this.messaging = messaging;
    hero.getChangeAnnouncer().addListener(flavor -> {
      if (ExperienceModelFetcher.fetch(hero).isExperienced()) {
        calculateXPCost();
      }
    });
    this.management = experiencePoints;
    this.view = overview;
  }

  public void initPresentation() {
    OverviewCategory category = view.addOverviewCategory(getString("Overview.Experience.Title"));
    for (IValueModel<Integer> model : management.getAllModels()) {
      StyledValueView<Integer> valueView = category.addIntegerValueView(getString("Overview.Experience." + model.getId()), 2);
      presenters.add(new ValueSubPresenter(model, valueView));
    }
    presenters.add(new TotalExperiencePresenter(hero, resources, messaging, management));
    initTotal(category);
    calculateXPCost();
  }

  private void initTotal(OverviewCategory category) {
    totalView = category.addAllotmentView(getString("Overview.Experience.Total"), 4);
    PointModelFetcher.fetch(hero).getExperiencePoints().addExperiencePointConfigurationListener(this::calculateXPCost);
  }

  private void calculateXPCost() {
    for (IOverviewSubPresenter presenter : presenters) {
      presenter.update();
    }
    totalView.setAllotment(getTotalXP());
    setTotalViewColor();
    totalView.setValue(management.getTotalCosts());
    setTotalViewColor();
  }

  private void setTotalViewColor() {
    boolean overspent = management.getTotalCosts() > getTotalXP();
    totalView.setTextColor(overspent ? LegalityColorProvider.COLOR_HIGH : LegalityColorProvider.COLOR_OKAY);
  }

  private int getTotalXP() {
    return PointModelFetcher.fetch(hero).getExperiencePoints().getTotalExperiencePoints();
  }

  private String getString(String string) {
    return resources.getString(string);
  }

  public void refresh() {
    calculateXPCost();
  }
}
