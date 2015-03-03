package net.sf.anathema.hero.merits.model.mechanics;

import net.sf.anathema.hero.merits.model.MechanicalDetailReference;

public interface MechanicalDetail {
  boolean isReferencedBy(MechanicalDetailReference reference);

  <T> T getEntry(DetailEntryReference reference);
}