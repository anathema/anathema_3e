package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.hero.application.creation.HeroModelFactory;
import net.sf.anathema.hero.application.creation.models.SimpleModelTreeEntry;
import net.sf.anathema.hero.environment.template.TemplateFactory;

@SuppressWarnings("UnusedDeclaration")
public class StatsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public StatsModelFactory() {
    super(StatsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public StatsModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new StatsModelImpl();
  }
}
