package net.sf.anathema.hero.merits.persistence;

import net.sf.anathema.library.persister.OptionalEntriesModelPto;
import net.sf.anathema.library.persister.PossessedOptionalTraitPto;

import java.util.ArrayList;
import java.util.List;

public class MeritsPto implements OptionalEntriesModelPto<PossessedOptionalTraitPto> {

  public List<PossessedOptionalTraitPto> merits = new ArrayList<>();

	@Override
	public List<PossessedOptionalTraitPto> getOptionalTraitPtoList() {
		return merits;
	}
}
