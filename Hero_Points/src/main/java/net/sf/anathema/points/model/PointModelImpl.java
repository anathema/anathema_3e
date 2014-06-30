package net.sf.anathema.points.model;

import net.sf.anathema.hero.points.advance.creation.BonusPointManagement;
import net.sf.anathema.hero.points.advance.creation.PointsCreationData;
import net.sf.anathema.hero.points.advance.experience.ExperiencePointConfiguration;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.advance.creation.HeroBonusPointCalculator;
import net.sf.anathema.hero.points.model.PointsModel;
import net.sf.anathema.hero.points.template.PointsTemplate;
import net.sf.anathema.hero.points.model.overview.IOverviewModel;
import net.sf.anathema.hero.points.model.overview.IValueModel;
import net.sf.anathema.hero.points.model.overview.WeightedCategory;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointModelImpl implements PointsModel {

  private final ExperiencePointConfiguration experiencePoints = new DefaultExperiencePointConfiguration();
  private final List<IValueModel<Integer>> experienceOverviewModels = new ArrayList<>();
  private final List<IOverviewModel> bonusOverviewModels = new ArrayList<>();
  private final List<WeightedCategory> bonusCategories = new ArrayList<>();
  private BonusPointManagement bonusPointManagement;

  public PointModelImpl(PointsTemplate template) {
    this.bonusPointManagement = new BonusPointManagement(new PointsCreationData(template));
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    experiencePoints.addExperiencePointConfigurationListener(new AnnounceExperiencePointChange(announcer));
  }

  @Override
  public void addBonusPointCalculator(HeroBonusPointCalculator calculator) {
    bonusPointManagement.addBonusPointCalculator(calculator);
  }

  @Override
  public void addBonusCategory(WeightedCategory category) {
    bonusCategories.add(category);
    Collections.sort(bonusCategories);
  }

  @Override
  public void addToBonusOverview(IOverviewModel bonusPointModel) {
    bonusOverviewModels.add(bonusPointModel);
  }

  @Override
  public void addToExperienceOverview(IValueModel<Integer> model) {
    experienceOverviewModels.add(model);
  }

  @Override
  public Iterable<IValueModel<Integer>> getExperienceOverviewModels() {
    return experienceOverviewModels;
  }

  @Override
  public Iterable<IOverviewModel> getBonusOverviewModels() {
    return bonusOverviewModels;
  }

  @Override
  public Iterable<WeightedCategory> getBonusCategories() {
    return bonusCategories;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  public BonusPointManagement getBonusPointManagement() {
    return bonusPointManagement;
  }

  @Override
  public ExperiencePointConfiguration getExperiencePoints() {
    return experiencePoints;
  }
}
