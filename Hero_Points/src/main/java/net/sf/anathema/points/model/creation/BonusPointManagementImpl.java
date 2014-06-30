package net.sf.anathema.points.model.creation;

import net.sf.anathema.points.model.BonusPointManagement;
import net.sf.anathema.points.model.overview.SpendingModel;

public class BonusPointManagementImpl implements BonusPointManagement {

  private final net.sf.anathema.points.model.creation.BonusPointCalculator bonusPointCalculator = new net.sf.anathema.points.model.creation.BonusPointCalculator();
  private final SpendingModel totalModel;

  public BonusPointManagementImpl(PointsCreationData creationData) {
    this.totalModel = new TotalBonusPointModel(creationData, bonusPointCalculator);
  }

  @Override
  public void recalculate() {
    bonusPointCalculator.recalculate();
  }

  public void addBonusPointCalculator(net.sf.anathema.points.model.BonusPointCalculator calculator) {
    bonusPointCalculator.addBonusPointCalculator(calculator);
  }

  @Override
  public SpendingModel getTotalModel() {
    return totalModel;
  }
}