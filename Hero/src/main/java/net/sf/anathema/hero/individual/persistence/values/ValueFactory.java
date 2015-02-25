package net.sf.anathema.hero.individual.persistence.values;

import net.sf.anathema.platform.persistence.common.ValueTemplate;

public interface ValueFactory {
	Value getValueForTemplate(ValueTemplate template);
}
