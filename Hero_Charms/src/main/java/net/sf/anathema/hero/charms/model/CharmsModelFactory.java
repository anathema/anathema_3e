package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.charms.template.model.CharmsTemplateLoader;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.health.model.HealthModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModel;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModel;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.points.model.PointsModel;

@SuppressWarnings("UnusedDeclaration")
public class CharmsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public CharmsModelFactory() {
    super(CharmsModel.ID, EssencePoolModel.ID, AttributeModel.ID, AbilitiesModel.ID, SpiritualTraitModel.ID, TraitModel.ID, ExperienceModel.ID,
            HeroConcept.ID, HealthModel.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public CharmsModel create(TemplateFactory templateFactory, String templateId) {
    CharmsTemplate charmsTemplate = CharmsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new CharmsModelImpl(charmsTemplate);
  }
}
