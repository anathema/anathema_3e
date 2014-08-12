package net.sf.anathema.hero.merits.model;

public class MeritImpl implements Merit {

	private final MeritOption optionBase;
	private final String description;
	
	public MeritImpl(MeritOption base, String description) {
		this.optionBase = base;
		this.description = description;
	}

	@Override
	public MeritOption getBaseOption() {
		return optionBase;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return optionBase.getId() + "(" + description + ")";
	}
}
