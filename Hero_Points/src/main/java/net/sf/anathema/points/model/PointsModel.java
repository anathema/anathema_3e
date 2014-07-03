package net.sf.anathema.points.model;

import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.points.model.overview.IOverviewModel;
import net.sf.anathema.points.model.overview.IValueModel;
import net.sf.anathema.points.model.overview.WeightedCategory;
import net.sf.anathema.points.model.xp.ExperiencePoints;

public interface PointsModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Points");

  void addBonusPointCalculator(BonusPointCalculator bonusPointCalculator);

  void addBonusCategory(WeightedCategory category);

  void addToBonusOverview(IOverviewModel bonusPointModel);

  void addToExperienceOverview(IValueModel<Integer> model);

  Iterable<IValueModel<Integer>> getExperienceOverviewModels();

  Iterable<IOverviewModel> getBonusOverviewModels();

  Iterable<WeightedCategory> getBonusCategories();

  BonusPointManagement getBonusPointManagement();

  ExperiencePoints getExperiencePoints();
}
