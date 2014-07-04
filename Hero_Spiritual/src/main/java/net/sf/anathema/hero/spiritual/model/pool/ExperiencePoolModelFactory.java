package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.spiritual.template.EssencePoolTemplate;
import net.sf.anathema.hero.spiritual.template.EssencePoolTemplateLoader;

@SuppressWarnings("UnusedDeclaration")
public class ExperiencePoolModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public ExperiencePoolModelFactory() {
    super(EssencePoolModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public EssencePoolModelImpl create(TemplateFactory templateFactory, String templateId) {
    EssencePoolTemplate template = EssencePoolTemplateLoader.loadTemplate(templateFactory, templateId);
    return new EssencePoolModelImpl(template);
  }
}
