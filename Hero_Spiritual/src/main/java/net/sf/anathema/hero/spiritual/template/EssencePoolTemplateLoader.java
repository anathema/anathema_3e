package net.sf.anathema.hero.spiritual.template;

import net.sf.anathema.hero.application.template.GenericTemplateLoader;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class EssencePoolTemplateLoader {

  public static EssencePoolTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<EssencePoolTemplate> loader = new GenericTemplateLoader<>(EssencePoolTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}