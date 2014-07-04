package net.sf.anathema.hero.concept.model.concept;

import com.google.common.base.Preconditions;
import net.sf.anathema.hero.application.creation.HeroModelFactory;
import net.sf.anathema.hero.application.creation.models.SimpleModelTreeEntry;
import net.sf.anathema.hero.concept.template.caste.CasteTemplate;
import net.sf.anathema.hero.concept.template.caste.CasteTemplateLoader;
import net.sf.anathema.hero.elsewhere.concept.HeroConcept;
import net.sf.anathema.hero.elsewhere.experience.ExperienceModel;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.points.model.PointsModel;

@SuppressWarnings("UnusedDeclaration")
public class CharacterConceptFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public CharacterConceptFactory() {
    super(HeroConcept.ID, ExperienceModel.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public DefaultHeroConcept create(TemplateFactory templateFactory, String templateId) {
    Preconditions.checkNotNull(templateId, "The Character Concept requires a configured template.");
    CasteTemplate template = CasteTemplateLoader.loadTemplate(templateFactory, templateId);
    DefaultCasteModel casteModel = new DefaultCasteModel(new DefaultCasteSelection(), new ConfigurableCasteCollection(template));
    return new DefaultHeroConcept(casteModel);
  }
}
