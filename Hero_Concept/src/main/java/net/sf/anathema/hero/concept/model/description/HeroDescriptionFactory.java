package net.sf.anathema.hero.concept.model.description;

import net.sf.anathema.hero.application.creation.HeroModelFactory;
import net.sf.anathema.hero.application.creation.models.SimpleModelTreeEntry;
import net.sf.anathema.hero.elsewhere.description.HeroDescription;
import net.sf.anathema.hero.elsewhere.experience.ExperienceModel;
import net.sf.anathema.hero.environment.template.TemplateFactory;

@SuppressWarnings("UnusedDeclaration")
public class HeroDescriptionFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HeroDescriptionFactory() {
    super(HeroDescription.ID, ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public HeroDescriptionImpl create(TemplateFactory templateFactory, String templateId) {
    return new HeroDescriptionImpl();
  }
}
