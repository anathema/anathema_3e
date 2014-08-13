package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.template.MeritPointsTemplate;
import net.sf.anathema.hero.merits.template.MeritPointsTemplateLoader;
import net.sf.anathema.points.model.PointsModel;

@SuppressWarnings("UnusedDeclaration")
public class MeritPointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public MeritPointsModelFactory() {
    super(MeritPointsModel.ID, MeritsModel.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public MeritPointsModel create(TemplateFactory templateFactory, String templateId) {
    MeritPointsTemplate template = MeritPointsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new MeritPointsModel(template);
  }
}
