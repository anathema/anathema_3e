package net.sf.anathema.library.model.property;


public abstract class AbstractPossessedProperty<O extends OptionalPropertyOption> implements PossessedOptionalProperty<O>{

	protected final O baseOption;
	protected final String description;
	protected final boolean isLearnedAtCreation;
	
	public AbstractPossessedProperty(O option, String description, boolean isLearnedAtCreation) {
		this.baseOption = option;
		this.description = description;
		this.isLearnedAtCreation = isLearnedAtCreation;
	}
	
	@Override
	public O getBaseOption() {
		return baseOption;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public boolean isLearnedAtCreation() {
		return isLearnedAtCreation;
	}

}
