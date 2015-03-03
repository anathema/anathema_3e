package net.sf.anathema.hero.thaumaturgy.compiler;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;
import net.sf.anathema.library.model.OptionalEntryOptionSupplier;

public interface ThaumaturgyRitualCache extends ExtensibleDataSet,
	OptionalEntryOptionSupplier<ThaumaturgyRitual> {
}