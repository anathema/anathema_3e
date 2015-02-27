package net.sf.anathema.library.persister;

import java.util.List;

public interface OptionalEntriesModelPto<P extends PossessedEntryPropertyPto> {
	List<P> getOptionalTraitPtoList();
}
