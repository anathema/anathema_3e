package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.library.model.trait.PossessedOptionalTrait;

public interface Merit extends PossessedOptionalTrait<MeritOption> {
  boolean hasMechanicalDetail(MechanicalDetailReference reference);

  MechanicalDetail getMechanicalDetail(MechanicalDetailReference reference);
}