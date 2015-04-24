package net.sf.anathema.library.persister;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.traits.model.state.NullTraitStateMap;
import net.sf.anathema.hero.traits.persistence.TraitPersister;
import net.sf.anathema.library.model.OptionalEntryReference;
import net.sf.anathema.library.model.trait.OptionalTraitOption;
import net.sf.anathema.library.model.trait.OptionalTraitsModel;
import net.sf.anathema.library.model.trait.PossessedOptionalTrait;

@SuppressWarnings("UnusedDeclaration")
public abstract class OptionalTraitsPersister<
	O extends OptionalTraitOption,
	T extends PossessedOptionalTrait<O>,
	M extends OptionalTraitsModel<O, T>,
	P extends OptionalEntriesModelPto<PossessedOptionalTraitPto>>
	extends AbstractModelJsonPersister<P, M> {

  private final TraitPersister traitPersister = new TraitPersister(new NullTraitStateMap());

  protected OptionalTraitsPersister(String name, Class<P> ptoClass) {
    super(name, ptoClass);
  }

  protected void loadModelFromPto(Hero hero, M model, P pto) {
    for (PossessedOptionalTraitPto traitPto : pto.getOptionalTraitPtoList()) {
      O option = model.findOptionByReference(new OptionalEntryReference(traitPto.option));
      model.setSelectedEntryOption(option);
      model.setCurrentDescription(traitPto.description);
      T newTrait = model.commitSelection();
      traitPersister.load(newTrait, traitPto.rating);
    }
    model.resetCurrentEntry();
  }

  protected P saveModelToPto(M heroModel) {
    P traitsList = createNewPto();
    for (T merit : heroModel.getEntries()) {
      PossessedOptionalTraitPto optionalTraitPto = createOptionalTraitPto(merit);
      traitsList.getOptionalTraitPtoList().add(optionalTraitPto);
    }
    return traitsList;
  }
  
  protected abstract P createNewPto();

  private PossessedOptionalTraitPto createOptionalTraitPto(T trait) {
    PossessedOptionalTraitPto pto = new PossessedOptionalTraitPto();
    pto.option = trait.getBaseOption().getTraitType().getId();
    pto.description = trait.getDescription();
    traitPersister.save(trait, pto.rating);
    return pto;
  }
}