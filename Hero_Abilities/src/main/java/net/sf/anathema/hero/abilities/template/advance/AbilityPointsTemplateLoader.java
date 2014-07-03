package net.sf.anathema.hero.abilities.template.advance;

import net.sf.anathema.hero.template.GenericTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class AbilityPointsTemplateLoader {

  public static AbilityPointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<AbilityPointsTemplate> loader = new GenericTemplateLoader<>(AbilityPointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}