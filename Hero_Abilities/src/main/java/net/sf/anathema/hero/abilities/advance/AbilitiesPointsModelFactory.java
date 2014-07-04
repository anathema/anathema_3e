package net.sf.anathema.hero.abilities.advance;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.template.advance.AbilityPointsTemplate;
import net.sf.anathema.hero.abilities.template.advance.AbilityPointsTemplateLoader;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.points.model.PointsModel;

@SuppressWarnings("UnusedDeclaration")
public class AbilitiesPointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AbilitiesPointsModelFactory() {
    super(AbilitiesPointModel.ID, AbilitiesModel.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public AbilitiesPointModel create(TemplateFactory templateFactory, String templateId) {
    AbilityPointsTemplate template = AbilityPointsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new AbilitiesPointModel(template);
  }
}
