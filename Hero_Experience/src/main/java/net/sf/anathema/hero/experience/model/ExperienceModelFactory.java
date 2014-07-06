package net.sf.anathema.hero.experience.model;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.history.model.HistoryModel;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;

@SuppressWarnings("UnusedDeclaration")
public class ExperienceModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public ExperienceModelFactory() {
    super(ExperienceModel.ID, HistoryModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public ExperienceModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new ExperienceModelImpl();
  }
}
