package net.sf.anathema.hero.martial.persistence;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.martial.model.MartialArtsModel;
import net.sf.anathema.hero.traits.persistence.TraitListPto;
import net.sf.anathema.library.identifier.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class MartialArtsPersister extends AbstractModelJsonPersister<TraitListPto, MartialArtsModel> {
  
  public MartialArtsPersister() {
    super("martialarts", TraitListPto.class);
  }

  @Override
  protected void loadModelFromPto(Hero hero, MartialArtsModel model, TraitListPto pto) {
    //TODO (URS) IMPLEMENT
  }

  @Override
  protected TraitListPto saveModelToPto(MartialArtsModel model) {
    //TODO (URS) IMPLEMENT
    return new TraitListPto();
  }

  @Override
  public Identifier getModelId() {
    return MartialArtsModel.ID;
  }
}
