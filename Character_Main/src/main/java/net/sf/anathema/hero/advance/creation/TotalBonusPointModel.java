package net.sf.anathema.hero.advance.creation;

import net.sf.anathema.hero.advance.overview.model.AbstractSpendingModel;

public class TotalBonusPointModel extends AbstractSpendingModel {

  public static final String SUMMARY_CATEGORY_ID = "Summary";
  private final PointsCreationData creationData;
  private BonusPointCalculator bonusPointCalculator;

  public TotalBonusPointModel(PointsCreationData creationData, BonusPointCalculator bonusPointCalculator) {
    super(SUMMARY_CATEGORY_ID, "Total");
    this.bonusPointCalculator = bonusPointCalculator;
    this.creationData = creationData;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return bonusPointCalculator.getTotalValue();
  }

  @Override
  public int getAllotment() {
    return creationData.getBonusPointAllowance() + bonusPointCalculator.getAdditionalGeneralBonusPoints();
  }
}
