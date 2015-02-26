package net.sf.anathema.library.persister;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.traits.model.state.NullTraitStateMap;
import net.sf.anathema.hero.traits.persistence.TraitPersister;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.KnownOptionalTrait;
import net.sf.anathema.library.model.OptionalTraitCategory;
import net.sf.anathema.library.model.OptionalTraitOption;
import net.sf.anathema.library.model.OptionalTraitReference;
import net.sf.anathema.library.model.OptionalTraitsModel;

@SuppressWarnings("UnusedDeclaration")
public abstract class OptionalTraitsPersister<
	C extends OptionalTraitCategory,
	O extends OptionalTraitOption,
	T extends KnownOptionalTrait<O>,
	M extends OptionalTraitsModel<C, O, T>,
	P extends OptionalTraitsModelPto> extends AbstractModelJsonPersister<P, M>{

  private final TraitPersister traitPersister = new TraitPersister(new NullTraitStateMap());

  protected OptionalTraitsPersister(String name, Class<P> ptoClass) {
    super(name, ptoClass);
  }

  protected void loadModelFromPto(Hero hero, M model, P pto) {
    for (KnownOptionalTraitPto traitPto : pto.getOptionalTraitPtoList()) {
      O option = model.findOptionByReference(new OptionalTraitReference(traitPto.option));
      model.setSelectedTraitOption(option);
      model.setCurrentDescription(traitPto.description);
      T newTrait = model.commitSelection();
      traitPersister.load(newTrait, traitPto.rating);
    }
    model.resetCurrentEntry();
  }

  protected P saveModelToPto(M heroModel) {
    P traitsList = createNewPto();
    for (T merit : heroModel.getEntries()) {
      traitsList.getOptionalTraitPtoList().add(createMeritsPto(merit));
    }
    return traitsList;
  }
  
  protected abstract P createNewPto();

  private KnownOptionalTraitPto createMeritsPto(T trait) {
    KnownOptionalTraitPto pto = new KnownOptionalTraitPto();
    pto.option = trait.getBaseOption().getId();
    pto.description = trait.getDescription();
    traitPersister.save(trait, pto.rating);
    return pto;
  }
}
