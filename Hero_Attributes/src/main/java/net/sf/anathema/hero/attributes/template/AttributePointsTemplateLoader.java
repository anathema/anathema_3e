package net.sf.anathema.hero.attributes.template;

import net.sf.anathema.hero.template.GenericTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class AttributePointsTemplateLoader {

  public static AttributePointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<AttributePointsTemplate> loader = new GenericTemplateLoader<>(AttributePointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}