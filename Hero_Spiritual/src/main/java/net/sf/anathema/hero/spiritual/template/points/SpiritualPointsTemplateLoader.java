package net.sf.anathema.hero.spiritual.template.points;

import net.sf.anathema.hero.template.GenericTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpiritualPointsTemplateLoader {

  public static SpiritualPointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<SpiritualPointsTemplate> loader = new GenericTemplateLoader<>(SpiritualPointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}