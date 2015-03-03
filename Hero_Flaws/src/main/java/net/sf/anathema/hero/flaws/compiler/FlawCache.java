package net.sf.anathema.hero.flaws.compiler;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.flaws.model.FlawOption;
import net.sf.anathema.library.model.OptionalEntryOptionSupplier;

public interface FlawCache extends ExtensibleDataSet, OptionalEntryOptionSupplier<FlawOption> {
}