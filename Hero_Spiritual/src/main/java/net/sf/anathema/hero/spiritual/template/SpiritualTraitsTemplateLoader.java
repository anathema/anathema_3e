package net.sf.anathema.hero.spiritual.template;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class SpiritualTraitsTemplateLoader {

  public static SpiritualTraitsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<SpiritualTraitsTemplate> loader = new GenericTemplateLoader<>(SpiritualTraitsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}