package net.sf.anathema.hero.charms.advance;

import net.sf.anathema.hero.application.creation.HeroModelFactory;
import net.sf.anathema.hero.application.creation.models.SimpleModelTreeEntry;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplateLoader;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.points.model.PointsModel;

@SuppressWarnings("UnusedDeclaration")
public class MagicPointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public MagicPointsModelFactory() {
    super(MagicPointsModel.ID, CharmsModel.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public MagicPointsModel create(TemplateFactory templateFactory, String templateId) {
    MagicPointsTemplate template = MagicPointsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new MagicPointsModel(template);
  }
}
