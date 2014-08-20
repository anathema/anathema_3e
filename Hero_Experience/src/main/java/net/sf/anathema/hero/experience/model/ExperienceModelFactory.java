package net.sf.anathema.hero.experience.model;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.individual.persistence.NullTemplateModel;

@SuppressWarnings("UnusedDeclaration")
@NullTemplateModel
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
