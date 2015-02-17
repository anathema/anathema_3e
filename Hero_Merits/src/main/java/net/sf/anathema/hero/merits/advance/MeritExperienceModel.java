package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.merits.advance.calculator.MeritExperienceCalculator;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.points.display.overview.model.AbstractIntegerValueModel;

public class MeritExperienceModel extends AbstractIntegerValueModel {

  private final MeritsModel model;
  private final MeritExperienceCalculator calculator;

  public MeritExperienceModel(String id, MeritsModel model, MeritExperienceCalculator calculator) {
    super(id, id);
    this.model = model;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    int totalXP = 0;
    for (Merit merit : model.getMerits()) {
      switch (merit.getBaseOption().getType()) {
        case Purchased:
          // only Purchased merits are paid for
          totalXP += calculator.getMeritCosts(merit);
        default:
          // nothing to do
      }
    }
    return totalXP;
  }
}