package net.sf.anathema.points.template;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.template.GenericTemplateLoader;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class PointsTemplateLoader {

  public static PointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<PointsTemplate> loader = new GenericTemplateLoader<>(PointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}