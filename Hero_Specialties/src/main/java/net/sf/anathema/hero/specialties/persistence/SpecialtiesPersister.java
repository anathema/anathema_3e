package net.sf.anathema.hero.specialties.persistence;

import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelImpl;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.library.identifier.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class SpecialtiesPersister extends AbstractModelJsonPersister<SpecialtiesPto, SpecialtiesModel> {

  public SpecialtiesPersister() {
    super("specialties", SpecialtiesPto.class);
  }

  @Override
  public Identifier getModelId() {
    return SpecialtiesModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, SpecialtiesModel model, SpecialtiesPto pto) {
    for (SpecialtyPto specialtyPto : pto.specialties) {
    	TraitType type = null;
    	for (IdentifiedTraitTypeList group : AbilitiesModelFetcher.fetch(hero).getGroups()) {
    		type = group.getById(specialtyPto.traitName);
    		if (type != null) {
    			break;
    		}
    	}
    	((SpecialtiesModelImpl)model).addSpecialty(hero, type, specialtyPto.specialtyName, specialtyPto.isCreationLearned);
    }
  }

  @Override
  protected SpecialtiesPto saveModelToPto(SpecialtiesModel model) {
    SpecialtiesPto pto = new SpecialtiesPto();
    for (Specialty specialty : model.getAllSpecialties()) {
      SpecialtyPto specialtyPto = new SpecialtyPto();
      specialtyPto.traitName = specialty.getBasicTraitType().toString();
      specialtyPto.specialtyName = specialty.getName();
      specialtyPto.isCreationLearned = specialty.isLearnedAtCreation();
      pto.specialties.add(specialtyPto);
    }
    return pto;
  }
}
