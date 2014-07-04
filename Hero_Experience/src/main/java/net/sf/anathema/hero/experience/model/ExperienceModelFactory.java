package net.sf.anathema.hero.experience.model;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;

@SuppressWarnings("UnusedDeclaration")
public class ExperienceModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public ExperienceModelFactory() {
    super(ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public ExperienceModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new ExperienceModelImpl();
  }
}
