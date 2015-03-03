package net.sf.anathema.hero.thaumaturgy.persister;

import net.sf.anathema.library.persister.OptionalEntriesModelPto;
import net.sf.anathema.library.persister.PossessedOptionalTraitPto;

import java.util.ArrayList;
import java.util.List;

public class ThaumaturgyPto implements OptionalEntriesModelPto<PossessedOptionalTraitPto> {

  public List<PossessedOptionalTraitPto> rituals = new ArrayList<>();

	@Override
	public List<PossessedOptionalTraitPto> getOptionalTraitPtoList() {
		return rituals;
	}
}
