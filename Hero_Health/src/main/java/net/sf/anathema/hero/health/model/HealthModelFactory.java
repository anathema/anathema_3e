package net.sf.anathema.hero.health.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.health.template.HealthTemplate;
import net.sf.anathema.hero.health.template.HealthTemplateLoader;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.traits.model.TraitModel;

@SuppressWarnings("UnusedDeclaration")
public class HealthModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HealthModelFactory() {
    super(HealthModel.ID, AbilitiesModel.ID, AttributeModel.ID, TraitModel.ID, ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public HealthModelImpl create(TemplateFactory templateFactory, String templateId) {
    HealthTemplate healthTemplate = HealthTemplateLoader.loadTemplate(templateFactory, templateId);
    return new HealthModelImpl(healthTemplate);
  }
}
