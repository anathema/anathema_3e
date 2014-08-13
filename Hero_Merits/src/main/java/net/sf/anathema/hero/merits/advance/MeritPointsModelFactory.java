package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.points.model.PointsModel;

@SuppressWarnings("UnusedDeclaration")
public class MeritPointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public MeritPointsModelFactory() {
    super(MeritsBonusPointsModel.ID, MeritsModel.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public MeritsBonusPointsModel create(TemplateFactory templateFactory, String templateId) {
    //AbilityPointsTemplate template = AbilityPointsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new MeritsBonusPointsModel();
  }
}
