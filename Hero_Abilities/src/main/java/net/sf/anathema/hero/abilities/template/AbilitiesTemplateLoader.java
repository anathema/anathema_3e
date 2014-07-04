package net.sf.anathema.hero.abilities.template;

import net.sf.anathema.hero.application.template.GenericTemplateLoader;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class AbilitiesTemplateLoader {

  public static AbilitiesTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<AbilitiesTemplate> loader = new GenericTemplateLoader<>(AbilitiesTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}