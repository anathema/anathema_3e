package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.points.model.PointsModel;

@SuppressWarnings("UnusedDeclaration")
public class MeritsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public MeritsModelFactory() {
    super(MeritsModel.ID, TraitModel.ID, ExperienceModel.ID, HeroConcept.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public MeritsModel create(TemplateFactory templateFactory, String templateId) {
    return new MeritsModelImpl();
  }
}
