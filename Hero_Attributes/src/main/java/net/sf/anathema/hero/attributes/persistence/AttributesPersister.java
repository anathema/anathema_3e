package net.sf.anathema.hero.attributes.persistence;

import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.persistence.TraitListPto;
import net.sf.anathema.hero.traits.persistence.TraitMapPersister;
import net.sf.anathema.library.identifier.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class AttributesPersister extends AbstractModelJsonPersister<TraitListPto, AttributeModel> {

  public AttributesPersister() {
    super("attributes", TraitListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return AttributeModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, AttributeModel model, TraitListPto pto) {
    TraitMapPersister traitMapPersister = createTraitMapPersister(model);
    traitMapPersister.loadTraitMap(model, pto);
  }

  @Override
  protected TraitListPto saveModelToPto(AttributeModel model) {
    TraitMapPersister traitMapPersister = createTraitMapPersister(model);
    return traitMapPersister.saveTraitMap(model);
  }

  private TraitMapPersister createTraitMapPersister(AttributeModel model) {
    return new TraitMapPersister(model.getStateMap(), AttributeType::valueOf);
  }

}
