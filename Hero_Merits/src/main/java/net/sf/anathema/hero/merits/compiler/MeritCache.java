package net.sf.anathema.hero.merits.compiler;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.library.model.OptionalEntryCategorySupplier;
import net.sf.anathema.library.model.OptionalEntryOptionSupplier;

public interface MeritCache extends ExtensibleDataSet,
  OptionalEntryCategorySupplier,
  OptionalEntryOptionSupplier<MeritOption> {
}