package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.hero.application.creation.HeroModelFactory;
import net.sf.anathema.hero.application.creation.models.SimpleModelTreeEntry;
import net.sf.anathema.hero.elsewhere.concept.HeroConcept;
import net.sf.anathema.hero.elsewhere.experience.ExperienceModel;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModel;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplateLoader;

@SuppressWarnings("UnusedDeclaration")
public class AttributeModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AttributeModelFactory() {
    super(AttributeModel.ID, SpiritualTraitModel.ID, TraitModel.ID, HeroConcept.ID, ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public AttributeModelImpl create(TemplateFactory templateFactory, String templateId) {
    GroupedTraitsTemplate template = GroupedTraitsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new AttributeModelImpl(template);
  }
}
