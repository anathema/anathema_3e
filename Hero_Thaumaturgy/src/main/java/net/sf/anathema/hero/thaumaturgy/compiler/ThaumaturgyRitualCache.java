package net.sf.anathema.hero.thaumaturgy.compiler;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;
import net.sf.anathema.library.model.NullCategory;
import net.sf.anathema.library.model.OptionalTraitCache;

public interface ThaumaturgyRitualCache extends ExtensibleDataSet,
	OptionalTraitCache<NullCategory, ThaumaturgyRitual> {
}