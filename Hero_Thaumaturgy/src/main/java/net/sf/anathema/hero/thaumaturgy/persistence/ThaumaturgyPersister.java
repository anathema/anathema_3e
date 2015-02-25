package net.sf.anathema.hero.thaumaturgy.persistence;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.thaumaturgy.model.KnownRitual;
import net.sf.anathema.hero.thaumaturgy.model.RitualReference;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModel;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;
import net.sf.anathema.hero.traits.model.state.NullTraitStateMap;
import net.sf.anathema.hero.traits.persistence.TraitPersister;
import net.sf.anathema.library.identifier.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class ThaumaturgyPersister extends AbstractModelJsonPersister<ThaumaturgyPto, ThaumaturgyModel> {

  private final TraitPersister traitPersister = new TraitPersister(new NullTraitStateMap());

  public ThaumaturgyPersister() {
    super("rituals", ThaumaturgyPto.class);
  }

  @Override
  protected void loadModelFromPto(Hero hero, ThaumaturgyModel model, ThaumaturgyPto pto) {
    for (RitualPto meritPto : pto.rituals) {
      ThaumaturgyRitual option = model.findOptionByReference(new RitualReference(meritPto.ritualOption));
      model.setCurrentRitual(option);
      model.setCurrentDescription(meritPto.description);
      KnownRitual newMerit = model.commitSelection();
      traitPersister.load(newMerit, meritPto.rating);
    }
    model.resetCurrentRitual();
  }

  @Override
  protected ThaumaturgyPto saveModelToPto(ThaumaturgyModel heroModel) {
    ThaumaturgyPto meritsList = new ThaumaturgyPto();
    for (KnownRitual merit : heroModel.getEntries()) {
      meritsList.rituals.add(createMeritsPto(merit));
    }
    return meritsList;
  }

  private RitualPto createMeritsPto(KnownRitual merit) {
    RitualPto pto = new RitualPto();
    pto.ritualOption = merit.getBaseOption().getId();
    pto.description = merit.getDescription();
    traitPersister.save(merit, pto.rating);
    return pto;
  }

  @Override
  public Identifier getModelId() {
    return ThaumaturgyModel.ID;
  }
}
