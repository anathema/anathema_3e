package net.sf.anathema.hero.specialties.template;

import net.sf.anathema.hero.template.GenericTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpecialtyPointsTemplateLoader {

  public static SpecialtyPointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<SpecialtyPointsTemplate> loader = new GenericTemplateLoader<>(SpecialtyPointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}