package net.sf.anathema.hero.health.template.merit;

import net.sf.anathema.hero.merits.compiler.template.mechanics.MeritMechanicalDetailTemplate;
import net.sf.anathema.hero.merits.model.mechanics.DetailEntryReference;
import net.sf.anathema.hero.merits.model.mechanics.GenericMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

import java.util.ArrayList;
import java.util.List;

@JsonType("AddsHealthLevels")
public class MeritHealthLevelTemplate extends MeritMechanicalDetailTemplate {
  public List<String> healthLevels = new ArrayList<>();

  @Override
  public MechanicalDetail generate() {
    GenericMechanicalDetail detail = new GenericMechanicalDetail("AddsHealthLevels");
    detail.addDetailEntry(new DetailEntryReference("healthLevels"), healthLevels);
    return detail;
  }
}