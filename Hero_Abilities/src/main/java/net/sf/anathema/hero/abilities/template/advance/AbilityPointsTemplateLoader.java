package net.sf.anathema.hero.abilities.template.advance;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class AbilityPointsTemplateLoader {

  public static AbilityPointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<AbilityPointsTemplate> loader = new GenericTemplateLoader<>(AbilityPointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}