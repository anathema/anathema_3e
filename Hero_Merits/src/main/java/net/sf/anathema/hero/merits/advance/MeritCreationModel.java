package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.merits.advance.calculator.MeritsBonusPointCalculator;
import net.sf.anathema.points.display.overview.model.AbstractSpendingModel;

public class MeritCreationModel extends AbstractSpendingModel {

  private final MeritCreationData creation;
  private final MeritsBonusPointCalculator calculator;

  public MeritCreationModel(String id, MeritCreationData creation, MeritsBonusPointCalculator calculator) {
    super(id, id);
    this.creation = creation;
    this.calculator = calculator;
  }

  @Override
  public int getAllotment() {
    return creation.getFreebiePoints();
  }

  @Override
  public int getSpentBonusPoints() {
    return calculator.getBonusPointCost();
  }

  @Override
  public Integer getValue() {
    return calculator.getPointsCoveredByFreebies();
  }
}