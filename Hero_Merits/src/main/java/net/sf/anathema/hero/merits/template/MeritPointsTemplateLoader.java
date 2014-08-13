package net.sf.anathema.hero.merits.template;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class MeritPointsTemplateLoader {

  public static MeritPointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<MeritPointsTemplate> loader = new GenericTemplateLoader<>(MeritPointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}