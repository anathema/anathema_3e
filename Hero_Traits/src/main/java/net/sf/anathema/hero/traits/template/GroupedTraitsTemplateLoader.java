package net.sf.anathema.hero.traits.template;

import net.sf.anathema.hero.application.template.GenericTemplateLoader;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class GroupedTraitsTemplateLoader {

  public static GroupedTraitsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<GroupedTraitsTemplate> loader = new GenericTemplateLoader<>(GroupedTraitsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}