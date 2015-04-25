package net.sf.anathema.hero.martial.template.merit;

import net.sf.anathema.hero.merits.compiler.template.mechanics.MeritMechanicalDetailTemplate;
import net.sf.anathema.hero.merits.model.mechanics.GenericMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("GrantsMartialArts")
public class GrantsMartialArts extends MeritMechanicalDetailTemplate {
  @Override
  public MechanicalDetail generate() {
    return new GenericMechanicalDetail("GrantsMartialArts");
  }
}
