package net.sf.anathema.hero.combos.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.combos.display.presenter.CombosModel;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.health.model.HealthModel;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModel;
import net.sf.anathema.hero.traits.model.TraitModel;

@SuppressWarnings("UnusedDeclaration")
public class CombosModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public CombosModelFactory() {
    super(CombosModel.ID, CharmsModel.ID, AttributeModel.ID, AbilitiesModel.ID, SpiritualTraitModel.ID, TraitModel.ID, ExperienceModel.ID, HeroConcept.ID,
            HealthModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public CombosModel create(TemplateFactory templateFactory, String templateId) {
    return new CombosModelImpl();
  }
}
