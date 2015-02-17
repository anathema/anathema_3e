package net.sf.anathema.hero.merits.compiler;

import java.util.List;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritReference;

public interface MeritCache extends ExtensibleDataSet {
	List<MeritOption> getAllMeritOptions();
	
	MeritOption getMeritOptionByName(MeritReference name);
}