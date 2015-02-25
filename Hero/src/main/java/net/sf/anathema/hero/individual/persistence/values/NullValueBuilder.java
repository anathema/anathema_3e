package net.sf.anathema.hero.individual.persistence.values;

import net.sf.anathema.platform.persistence.common.ValueTemplate;

public class NullValueBuilder implements ValueBuilder {

	@Override
	public Value getValueForTemplate(ValueTemplate template) {
		return null;
	}

	@Override
	public boolean supportsTemplate(ValueTemplate template) {
		return false;
	}

}
