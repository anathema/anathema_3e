package net.sf.anathema.hero.health.template.merit;

import net.sf.anathema.hero.merits.compiler.template.mechanics.MeritMechanicalDetailTemplate;
import net.sf.anathema.hero.merits.model.mechanics.DetailEntryReference;
import net.sf.anathema.hero.merits.model.mechanics.GenericMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("AddsPainTolerance")
public class MeritPainToleranceTemplate extends MeritMechanicalDetailTemplate {
  public int tolerance;

  @Override
  public MechanicalDetail generate() {
    GenericMechanicalDetail detail = new GenericMechanicalDetail("AddsPainTolerance");
    detail.addDetailEntry(new DetailEntryReference("tolerance"), tolerance);
    return detail;
  }
}
