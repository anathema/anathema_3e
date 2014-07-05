package net.sf.anathema.hero.concept.template.caste;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class CasteListTemplateLoader {

  public static CasteListTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<CasteListTemplate> loader = new GenericTemplateLoader<>(CasteListTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}