package net.sf.anathema.hero.thaumaturgy.template.merit;

import net.sf.anathema.hero.merits.compiler.template.mechanics.MeritMechanicalDetailTemplate;
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