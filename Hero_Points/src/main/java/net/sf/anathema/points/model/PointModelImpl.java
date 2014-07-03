package net.sf.anathema.points.model;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.points.model.creation.BonusPointManagementImpl;
import net.sf.anathema.points.model.creation.PointsCreationData;
import net.sf.anathema.points.model.overview.IOverviewModel;
import net.sf.anathema.points.model.overview.IValueModel;
import net.sf.anathema.points.model.overview.WeightedCategory;
import net.sf.anathema.points.model.xp.ExperiencePoints;
import net.sf.anathema.points.template.PointsTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointModelImpl implements PointsModel {

  private final ExperiencePoints experiencePoints = new DefaultExperiencePointConfiguration();
  private final List<IValueModel<Integer>> experienceOverviewModels = new ArrayList<>();
  private final List<IOverviewModel> bonusOverviewModels = new ArrayList<>();
  private final List<WeightedCategory> bonusCategories = new ArrayList<>();
  private BonusPointManagementImpl bonusPointManagement;

  public PointModelImpl(PointsTemplate template) {
    this.bonusPointManagement = new BonusPointManagementImpl(new PointsCreationData(template));
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
  public void addBonusPointCalculator(BonusPointCalculator calculator) {
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

  public BonusPointManagementImpl getBonusPointManagement() {
    return bonusPointManagement;
  }

  @Override
  public ExperiencePoints getExperiencePoints() {
    return experiencePoints;
  }
}
