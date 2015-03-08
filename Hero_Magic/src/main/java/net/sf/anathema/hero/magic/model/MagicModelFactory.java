package net.sf.anathema.hero.magic.model;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;

@SuppressWarnings("UnusedDeclaration")
public class MagicModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {
  public MagicModelFactory() {
    super(MagicModel.ID);
  }

  @Override
  public MagicModel create(TemplateFactory templateFactory, String templateId) {
    return new MagicModel();
  }
}
