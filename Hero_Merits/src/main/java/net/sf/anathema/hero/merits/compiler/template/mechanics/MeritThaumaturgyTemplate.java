package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.hero.merits.model.mechanics.GenericMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("GrantsThaumaturgy")
public class MeritThaumaturgyTemplate extends MeritMechanicalDetailTemplate {

  @Override
  public MechanicalDetail generate() {
    return new GenericMechanicalDetail("GrantsThaumaturgy");
  }
}