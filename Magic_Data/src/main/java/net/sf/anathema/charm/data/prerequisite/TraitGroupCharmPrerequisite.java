package net.sf.anathema.charm.data.prerequisite;

import java.util.List;

import com.google.common.collect.Lists;

import net.sf.anathema.library.lang.ReflectionEqualsObject;

public class TraitGroupCharmPrerequisite extends ReflectionEqualsObject implements CharmPrerequisite {

	private final List<RequiredTraitType> traits;
	private final int threshold;
	private final int minimumEssence;
	
	public TraitGroupCharmPrerequisite(List<RequiredTraitType> traits, int count, int minimumEssence) {
		this.traits = traits;
		this.threshold = count;
		this.minimumEssence = minimumEssence;
	}
	
	@Override
	public void process(PrerequisiteProcessor processor) {
		processor.requiresCharmsOfTraits(traits, threshold, minimumEssence);
	}

	@Override
	public void accept(PrerequisiteVisitor visitor) {
		visitor.visit(this);
	}
	
	public List<RequiredTraitType> getTraits() {
		return traits;
	}
	
	public List<String> getTraitsAsStrings() {
		return Lists.transform(traits, trait -> trait.type);
	}
	
	public int getThreshold() {
		return threshold;
	}
	
	public int getEssenceMinimum() {
		return minimumEssence;
	}
	
	public String getLabel() {
		String label = "";
		for (RequiredTraitType trait : traits) {
			label += trait.type;
		}
		return label;
	}

}
