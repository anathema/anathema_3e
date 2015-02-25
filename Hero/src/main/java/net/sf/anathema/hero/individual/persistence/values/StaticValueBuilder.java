package net.sf.anathema.hero.individual.persistence.values;

import net.sf.anathema.platform.persistence.common.StaticValueTemplate;
import net.sf.anathema.platform.persistence.common.ValueTemplate;

public class StaticValueBuilder implements ValueBuilder {

	@Override
	public Value getValueForTemplate(ValueTemplate template) {
		StaticValueTemplate staticTemplate = (StaticValueTemplate) template;
		return new StaticValue(staticTemplate.value);
	}

	@Override
	public boolean supportsTemplate(ValueTemplate template) {
		return template instanceof StaticValueTemplate;
	}

}
