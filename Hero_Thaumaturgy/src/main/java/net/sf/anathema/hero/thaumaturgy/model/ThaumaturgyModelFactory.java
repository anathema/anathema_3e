package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.points.model.PointsModel;

@SuppressWarnings("UnusedDeclaration")
public class ThaumaturgyModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public ThaumaturgyModelFactory() {
    super(ThaumaturgyModel.ID, TraitModel.ID, ExperienceModel.ID, HeroConcept.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public ThaumaturgyModel create(TemplateFactory templateFactory, String templateId) {
    return new ThaumaturgyModelImpl();
  }
}
