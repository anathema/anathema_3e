package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.points.display.overview.model.AbstractSpendingModel;

public class DefaultMagicModel extends AbstractSpendingModel {

  private final MagicCreationCostCalculator magicCalculator;
  private MagicCreationData creationData;

  public DefaultMagicModel(MagicCreationCostCalculator magicCalculator, MagicCreationData creationData) {
    super("Magic", "General");
    this.magicCalculator = magicCalculator;
    this.creationData = creationData;
  }

  @Override
  public int getSpentBonusPoints() {
    if (magicCalculator == null) {
      return 0;
    }
    return magicCalculator.getBonusPointCost();
  }

  @Override
  public Integer getValue() {
    return magicCalculator.getGeneralMagicPicksSpent();
  }

  @Override
  public int getAllotment() {
    return creationData.getGeneralMagicPicks();
  }
}