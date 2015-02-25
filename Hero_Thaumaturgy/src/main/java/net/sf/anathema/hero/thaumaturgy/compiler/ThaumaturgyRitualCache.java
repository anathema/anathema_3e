package net.sf.anathema.hero.thaumaturgy.compiler;

import java.util.List;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.thaumaturgy.model.RitualReference;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;

public interface ThaumaturgyRitualCache extends ExtensibleDataSet {
	List<ThaumaturgyRitual> getAllRitualOptions();
	
	ThaumaturgyRitual getRitualByReference(RitualReference reference);
}