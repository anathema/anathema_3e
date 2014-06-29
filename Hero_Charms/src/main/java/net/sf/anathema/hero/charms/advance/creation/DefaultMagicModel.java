package net.sf.anathema.hero.charms.advance.creation;

import net.sf.anathema.hero.advance.overview.model.AbstractSpendingModel;

public class DefaultMagicModel extends AbstractSpendingModel {

  private final MagicCreationCostCalculator magicCalculator;
  private MagicCreationData creationData;

  public DefaultMagicModel(MagicCreationCostCalculator magicCalculator, MagicCreationData creationData) {
    super("Charms", "General");
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
    return magicCalculator.getGeneralCharmPicksSpent();
  }

  @Override
  public int getAllotment() {
    return creationData.getGeneralMagicPicks();
  }
}