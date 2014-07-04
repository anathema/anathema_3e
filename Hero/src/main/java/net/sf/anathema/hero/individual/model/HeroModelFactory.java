package net.sf.anathema.hero.individual.model;

import net.sf.anathema.hero.environment.template.TemplateFactory;

public interface HeroModelFactory extends ModelTreeEntry {

  <M extends HeroModel> M create(TemplateFactory templateFactory, String templateId);
}
