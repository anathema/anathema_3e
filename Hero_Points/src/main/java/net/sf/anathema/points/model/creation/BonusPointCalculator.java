package net.sf.anathema.points.model.creation;

import java.util.ArrayList;
import java.util.List;

public class BonusPointCalculator {

  private final List<net.sf.anathema.points.model.BonusPointCalculator> allCalculators = new ArrayList<>();

  public void addBonusPointCalculator(net.sf.anathema.points.model.BonusPointCalculator additionalCalculator) {
    allCalculators.add(additionalCalculator);
  }

  public void recalculate() {
    for (net.sf.anathema.points.model.BonusPointCalculator calculator : allCalculators) {
      calculator.recalculate();
    }
  }

  public int getAdditionalGeneralBonusPoints() {
    int additionalGranted = 0;
    for (net.sf.anathema.points.model.BonusPointCalculator calculator : allCalculators) {
      additionalGranted += calculator.getBonusPointsGranted();
    }
    return additionalGranted;
  }

  public int getTotalValue() {
    int pointsSpent = 0;
    for (net.sf.anathema.points.model.BonusPointCalculator calculator : allCalculators) {
      pointsSpent += calculator.getBonusPointCost();
    }
    return pointsSpent;
  }
}