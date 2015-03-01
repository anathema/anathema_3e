package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.merits.model.mechanics.DetailEntryReference;
import net.sf.anathema.hero.merits.model.mechanics.GenericMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@JsonType("AddsHealthLevels")
public class MeritHealthLevelTemplate extends MeritMechanicalDetailTemplate {
  public List<HealthLevelType> healthLevels = new ArrayList<>();

  @Override
  public MechanicalDetail generate() {
    GenericMechanicalDetail detail = new GenericMechanicalDetail("AddsHealthLevels");
    detail.addDetailEntry(new DetailEntryReference("healthLevels"), healthLevels.stream().map(healthLevel -> String.valueOf(healthLevel.getIntValue())).collect(toList()));
    return detail;
  }
}