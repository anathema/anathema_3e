package net.sf.anathema.hero.thaumaturgy.persister;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.library.persister.KnownOptionalTraitPto;
import net.sf.anathema.library.persister.OptionalTraitsModelPto;

public class ThaumaturgyPto implements OptionalTraitsModelPto {

  public List<KnownOptionalTraitPto> rituals = new ArrayList<>();

	@Override
	public List<KnownOptionalTraitPto> getOptionalTraitPtoList() {
		return rituals;
	}
}
