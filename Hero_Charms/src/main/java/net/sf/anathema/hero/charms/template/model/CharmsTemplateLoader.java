package net.sf.anathema.hero.charms.template.model;

import net.sf.anathema.hero.application.template.GenericTemplateLoader;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class CharmsTemplateLoader {

  public static CharmsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<CharmsTemplate> loader = new GenericTemplateLoader<>(CharmsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}