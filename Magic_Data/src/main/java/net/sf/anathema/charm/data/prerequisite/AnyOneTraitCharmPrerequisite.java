package net.sf.anathema.charm.data.prerequisite;

public class AnyOneTraitCharmPrerequisite implements CharmPrerequisite {

	private final int threshold;
	
	public AnyOneTraitCharmPrerequisite(int threshold) {
		this.threshold = threshold;
	}
	
	@Override
	public void process(PrerequisiteProcessor processor) {
		processor.requiresCharmsOfAnyOneTrait(threshold);
	}

	@Override
	public void accept(PrerequisiteVisitor visitor) {
		visitor.visit(this);
	}
	
	public int getThreshold() {
		return threshold;
	}

}
