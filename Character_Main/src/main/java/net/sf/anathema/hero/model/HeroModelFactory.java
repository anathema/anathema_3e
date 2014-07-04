package net.sf.anathema.hero.model;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.initialization.ModelTreeEntry;

public interface HeroModelFactory extends ModelTreeEntry {

  <M extends HeroModel> M create(TemplateFactory templateFactory, String templateId);
}
