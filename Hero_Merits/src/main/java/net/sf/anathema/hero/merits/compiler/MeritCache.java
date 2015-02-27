package net.sf.anathema.hero.merits.compiler;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.merits.model.MeritCategory;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.library.model.OptionalEntryCache;

public interface MeritCache extends ExtensibleDataSet,
	OptionalEntryCache<MeritCategory, MeritOption> {
}