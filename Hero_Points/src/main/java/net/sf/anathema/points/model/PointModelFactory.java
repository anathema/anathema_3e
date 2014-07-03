package net.sf.anathema.points.model;

import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;
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
