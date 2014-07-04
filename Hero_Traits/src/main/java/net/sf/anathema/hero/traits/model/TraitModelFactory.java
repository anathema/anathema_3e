package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;

@SuppressWarnings("UnusedDeclaration")
public class TraitModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public TraitModelFactory() {
    super(TraitModel.ID, ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public TraitModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new TraitModelImpl();
  }
}
