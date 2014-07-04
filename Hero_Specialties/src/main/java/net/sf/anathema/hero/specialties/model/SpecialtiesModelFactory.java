package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.application.creation.HeroModelFactory;
import net.sf.anathema.hero.application.creation.models.SimpleModelTreeEntry;
import net.sf.anathema.hero.environment.template.TemplateFactory;

@SuppressWarnings("UnusedDeclaration")
public class SpecialtiesModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public SpecialtiesModelFactory() {
    super(SpecialtiesModel.ID, AbilitiesModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public SpecialtiesModel create(TemplateFactory templateFactory, String templateId) {
    return new SpecialtiesModelImpl();
  }
}
