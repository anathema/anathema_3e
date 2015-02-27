package net.sf.anathema.library.model.property;

import net.sf.anathema.library.model.trait.OptionalTraitOption;

public abstract class AbstractPossessedProperty<O extends OptionalTraitOption> implements PossessedOptionalProperty<O>{

	protected final O baseOption;
	protected final String description;
	
	public AbstractPossessedProperty(O option, String description) {
		this.baseOption = option;
		this.description = description;
	}
	
	@Override
	public O getBaseOption() {
		return baseOption;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
