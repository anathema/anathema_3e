package net.sf.anathema.hero.merits.persistence;

import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.persister.OptionalTraitsPersister;

@SuppressWarnings("UnusedDeclaration")
public class MeritPersister 
  extends OptionalTraitsPersister<MeritOption, Merit, MeritsModel, MeritsPto>{

  public MeritPersister() {
    super("merits", MeritsPto.class);
  }

  @Override
  public Identifier getModelId() {
    return MeritsModel.ID;
  }

	@Override
	protected MeritsPto createNewPto() {
		return new MeritsPto();
	}
}
