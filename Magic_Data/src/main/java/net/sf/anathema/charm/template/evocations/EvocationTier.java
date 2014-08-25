package net.sf.anathema.charm.template.evocations;

import net.sf.anathema.library.identifier.Identifier;

public enum EvocationTier implements Identifier, Comparable<EvocationTier> {
	None, Emerald, Sapphire, Adamant;
	
	public EvocationTier getPriorTier() {
		return EvocationTier.values()[this.ordinal() - 1];
	}

	@Override
	public String getId() {
		return toString();
	}
}
