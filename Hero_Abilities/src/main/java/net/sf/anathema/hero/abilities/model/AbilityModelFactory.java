package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.abilities.template.AbilitiesTemplate;
import net.sf.anathema.hero.abilities.template.AbilitiesTemplateLoader;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModel;
import net.sf.anathema.hero.traits.model.TraitModel;

@SuppressWarnings("UnusedDeclaration")
public class AbilityModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AbilityModelFactory() {
    super(AbilitiesModel.ID, SpiritualTraitModel.ID, TraitModel.ID, HeroConcept.ID, ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public AbilitiesModelImpl create(TemplateFactory templateFactory, String templateId) {
    AbilitiesTemplate template = AbilitiesTemplateLoader.loadTemplate(templateFactory, templateId);
    return new AbilitiesModelImpl(template);
  }
}
