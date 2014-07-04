package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

@SuppressWarnings("UnusedDeclaration")
public class SolarLimitBreakModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  private static final Identifier FACTORY_ID = new SimpleIdentifier("SolarVirtueFlaw");

  public SolarLimitBreakModelFactory() {
    super(FACTORY_ID, SpiritualTraitModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public DescriptiveLimitBreakModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new DescriptiveLimitBreakModelImpl();
  }
}