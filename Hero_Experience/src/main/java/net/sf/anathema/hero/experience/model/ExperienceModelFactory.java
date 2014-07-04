package net.sf.anathema.hero.experience.model;

import net.sf.anathema.hero.application.creation.HeroModelFactory;
import net.sf.anathema.hero.application.creation.models.SimpleModelTreeEntry;
import net.sf.anathema.hero.elsewhere.experience.ExperienceModel;
import net.sf.anathema.hero.environment.template.TemplateFactory;

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
