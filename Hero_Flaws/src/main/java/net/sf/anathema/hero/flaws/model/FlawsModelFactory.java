package net.sf.anathema.hero.flaws.model;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;

@SuppressWarnings("UnusedDeclaration")
public class FlawsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public FlawsModelFactory() {
    super(FlawsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public FlawsModel create(TemplateFactory templateFactory, String templateId) {
    return new FlawsModelImpl();
  }
}
