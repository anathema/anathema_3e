package net.sf.anathema.hero.specialties.advance;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.specialties.template.SpecialtyPointsTemplate;
import net.sf.anathema.hero.specialties.template.SpecialtyPointsTemplateLoader;
import net.sf.anathema.points.model.PointsModel;

@SuppressWarnings("UnusedDeclaration")
public class SpecialtiesPointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public SpecialtiesPointsModelFactory() {
    super(SpecialtiesPointsModel.ID, AbilitiesModel.ID, AttributeModel.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public SpecialtiesPointsModel create(TemplateFactory templateFactory, String templateId) {
    SpecialtyPointsTemplate template = SpecialtyPointsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new SpecialtiesPointsModel(template);
  }
}
