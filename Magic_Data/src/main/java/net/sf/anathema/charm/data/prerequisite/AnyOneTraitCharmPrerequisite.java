package net.sf.anathema.charm.data.prerequisite;

import net.sf.anathema.charm.data.Charm;

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

  public void excludeSpecificPrerequsitesOf(Charm charm) {
    //TODO IMPLEMENT
  }
}
