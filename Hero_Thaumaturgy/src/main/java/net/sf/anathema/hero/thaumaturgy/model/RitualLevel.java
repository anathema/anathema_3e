package net.sf.anathema.hero.thaumaturgy.model;

public enum RitualLevel {
	Basic, Advanced;
	
	public int getValue() {
		return ordinal() + 1;
	}
	
	public static RitualLevel getMinimumLevel() {
		return RitualLevel.values()[0];
	}
	
	public static RitualLevel getMaximumLevel() {
		return RitualLevel.values()[RitualLevel.values().length - 1];
	}
}
