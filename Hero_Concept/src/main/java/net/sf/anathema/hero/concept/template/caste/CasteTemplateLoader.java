package net.sf.anathema.hero.concept.template.caste;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.template.GenericTemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class CasteTemplateLoader {

  public static CasteTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<CasteTemplate> loader = new GenericTemplateLoader<>(CasteTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}