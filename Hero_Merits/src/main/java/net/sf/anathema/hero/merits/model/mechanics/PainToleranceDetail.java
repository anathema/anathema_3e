package net.sf.anathema.hero.merits.model.mechanics;

public class PainToleranceDetail {
  private MechanicalDetail detail;

  public PainToleranceDetail(MechanicalDetail detail) {
    this.detail = detail;
  }

  public int getTolerance() {
    return detail.getEntry(new DetailEntryReference("tolerance"));
  }
}
