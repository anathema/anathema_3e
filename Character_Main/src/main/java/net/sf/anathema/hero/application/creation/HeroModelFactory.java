package net.sf.anathema.hero.application.creation;

import net.sf.anathema.hero.application.creation.models.ModelTreeEntry;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModel;

public interface HeroModelFactory extends ModelTreeEntry {

  <M extends HeroModel> M create(TemplateFactory templateFactory, String templateId);
}
