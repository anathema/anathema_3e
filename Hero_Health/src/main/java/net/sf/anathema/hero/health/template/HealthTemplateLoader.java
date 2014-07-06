package net.sf.anathema.hero.health.template;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.individual.persistence.GenericAdapter;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class HealthTemplateLoader {

  public static HealthTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    GenericAdapter<HealthLevelType> adapter = new GenericAdapter<>(HealthLevelType.class, new HealthLevelReader());
    TemplateLoader<HealthTemplate> loader = new GenericTemplateLoader<HealthTemplate>(HealthTemplate.class, adapter);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}
