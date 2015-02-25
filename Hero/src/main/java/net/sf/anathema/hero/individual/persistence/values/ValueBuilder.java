package net.sf.anathema.hero.individual.persistence.values;

import net.sf.anathema.platform.persistence.common.ValueTemplate;

public interface ValueBuilder {
	Value getValueForTemplate(ValueTemplate template);
	
	boolean supportsTemplate(ValueTemplate template);
}
