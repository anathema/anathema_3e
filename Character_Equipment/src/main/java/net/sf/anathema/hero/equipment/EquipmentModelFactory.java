package net.sf.anathema.hero.equipment;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.sheet.pdf.content.stats.StatsModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;

@SuppressWarnings("UnusedDeclaration")
public class EquipmentModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public EquipmentModelFactory() {
    super(EquipmentModel.ID, AbilitiesModel.ID, SpecialtiesModel.ID, StatsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public EquipmentModel create(TemplateFactory templateFactory, String templateId) {
    return new EquipmentModelImpl();
  }
}
