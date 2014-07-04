package net.sf.anathema.points.model;

import net.sf.anathema.hero.application.creation.HeroModelFactory;
import net.sf.anathema.hero.application.creation.models.SimpleModelTreeEntry;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.points.template.PointsTemplate;
import net.sf.anathema.points.template.PointsTemplateLoader;

@SuppressWarnings("UnusedDeclaration")
public class PointModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public PointModelFactory() {
    super(PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public PointsModel create(TemplateFactory templateFactory, String templateId) {
    PointsTemplate template = PointsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new PointModelImpl(template);
  }
}
