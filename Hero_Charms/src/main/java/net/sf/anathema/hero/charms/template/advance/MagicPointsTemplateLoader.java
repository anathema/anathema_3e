package net.sf.anathema.hero.charms.template.advance;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class MagicPointsTemplateLoader {

  public static MagicPointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<MagicPointsTemplate> loader = new GenericTemplateLoader<>(MagicPointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}