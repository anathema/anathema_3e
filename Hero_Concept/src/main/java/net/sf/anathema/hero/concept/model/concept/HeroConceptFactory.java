package net.sf.anathema.hero.concept.model.concept;

import com.google.common.base.Preconditions;
import net.sf.anathema.hero.concept.template.caste.CasteListTemplate;
import net.sf.anathema.hero.concept.template.caste.CasteListTemplateLoader;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;

@SuppressWarnings("UnusedDeclaration")
public class HeroConceptFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HeroConceptFactory() {
    super(HeroConcept.ID, ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public HeroConceptImpl create(TemplateFactory templateFactory, String templateId) {
    Preconditions.checkNotNull(templateId, "The Character Concept requires a configured template.");
    CasteListTemplate template = CasteListTemplateLoader.loadTemplate(templateFactory, templateId);
    CasteModelImpl casteModel = new CasteModelImpl(new CasteSelectionImpl(), new CasteCollectionImpl(template));
    return new HeroConceptImpl(casteModel);
  }
}
