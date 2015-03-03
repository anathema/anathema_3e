package net.sf.anathema.library.model;


public interface PossessedOptionalEntry<O extends OptionalEntryOption> {
	O getBaseOption();
	
	String getDescription();
}
