package net.sf.anathema.hero.charms.template.model;

import net.sf.anathema.hero.template.GenericTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class CharmsTemplateLoader {

  public static CharmsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<CharmsTemplate> loader = new GenericTemplateLoader<>(CharmsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}