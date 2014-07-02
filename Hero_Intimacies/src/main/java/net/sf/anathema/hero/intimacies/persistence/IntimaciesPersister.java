package net.sf.anathema.hero.intimacies.persistence;

import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.lib.util.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class IntimaciesPersister extends AbstractModelJsonPersister<IntimaciesPto, IntimaciesModel> {

  public IntimaciesPersister() {
    super("intimacies", IntimaciesPto.class);
  }

  @Override
  protected void loadModelFromPto(Hero hero, IntimaciesModel model, IntimaciesPto pto) {
    for (IntimacyPto intimacyPto : pto.intimacies) {
      model.setCurrentName(intimacyPto.name);
      model.setCurrentStrength(intimacyPto.strength);
      model.setCurrentOutlook(intimacyPto.outlook);
      model.setCurrentBond(intimacyPto.bond);
      model.commitSelection();
    }
  }

  @Override
  protected IntimaciesPto saveModelToPto(IntimaciesModel heroModel) {
    IntimaciesPto intimaciesList = new IntimaciesPto();
    for (Intimacy intimacy : heroModel.getEntries()) {
      intimaciesList.intimacies.add(createIntimacyPto(intimacy));
    }
    return intimaciesList;
  }

  private IntimacyPto createIntimacyPto(Intimacy intimacy) {
    IntimacyPto pto = new IntimacyPto();
    pto.name = intimacy.getName();
    pto.strength = intimacy.getStrength();
    pto.outlook = intimacy.getOutlook();
    pto.bond = intimacy.getBond();
    return pto;
  }

  @Override
  public Identifier getModelId() {
    return IntimaciesModel.ID;
  }
}
