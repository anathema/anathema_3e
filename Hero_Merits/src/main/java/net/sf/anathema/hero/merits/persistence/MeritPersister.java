package net.sf.anathema.hero.merits.persistence;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.library.identifier.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class MeritPersister extends AbstractModelJsonPersister<MeritsPto, MeritsModel> {

  public MeritPersister() {
    super("intimacies", MeritsPto.class);
  }

  @Override
  protected void loadModelFromPto(Hero hero, MeritsModel model, MeritsPto pto) {
    for (MeritPto meritPto : pto.merits) {
      model.setCurrentMerit(meritPto.meritOption);
      model.setCurrentDescription(meritPto.description);
      model.commitSelection();
      // TODO: Trait
    }
  }

  @Override
  protected MeritsPto saveModelToPto(MeritsModel heroModel) {
    MeritsPto meritsList = new MeritsPto();
    for (Merit merit : heroModel.getEntries()) {
      meritsList.merits.add(createMeritsPto(merit));
    }
    return meritsList;
  }

  private MeritPto createMeritsPto(Merit merit) {
    MeritPto pto = new MeritPto();
    pto.meritOption = merit.getBaseOption().getId();
    pto.description = merit.getDescription();
    // TODO: Trait
    return pto;
  }

  @Override
  public Identifier getModelId() {
    return MeritsModel.ID;
  }
}
