package net.sf.anathema.hero.history.model;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;

@SuppressWarnings("UnusedDeclaration")
public class HistoryModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HistoryModelFactory() {
    super(HistoryModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public HistoryModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new HistoryModelImpl();
  }
}
