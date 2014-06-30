package net.sf.anathema.points.model;

import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.points.model.PointsModel;
import net.sf.anathema.hero.points.template.PointsTemplate;
import net.sf.anathema.hero.points.template.PointsTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;

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
