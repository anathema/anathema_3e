package net.sf.anathema.hero.health.model.merit;

import net.sf.anathema.hero.merits.model.mechanics.DetailEntryReference;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;

public class PainToleranceDetail {
  private MechanicalDetail detail;

  public PainToleranceDetail(MechanicalDetail detail) {
    this.detail = detail;
  }

  public int getTolerance() {
    return detail.getEntry(new DetailEntryReference("tolerance"));
  }
}
