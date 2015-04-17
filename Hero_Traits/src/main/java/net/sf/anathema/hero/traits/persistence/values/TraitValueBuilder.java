package net.sf.anathema.hero.traits.persistence.values;

import net.sf.anathema.hero.individual.persistence.values.Value;
import net.sf.anathema.hero.individual.persistence.values.ValueBuilder;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.platform.persistence.common.ValueTemplate;

public class TraitValueBuilder implements ValueBuilder {

	@Override
	public Value getValueForTemplate(ValueTemplate template) {
		TraitValueTemplate traitTemplate = (TraitValueTemplate) template;
		TraitType trait = new TraitType(traitTemplate.trait);
		return new TraitValue(trait);
	}

	@Override
	public boolean supportsTemplate(ValueTemplate template) {
		return template instanceof TraitValueTemplate;
	}

}
