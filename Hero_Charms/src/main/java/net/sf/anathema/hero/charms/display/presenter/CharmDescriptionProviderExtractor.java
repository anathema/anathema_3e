package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.magic.description.model.AggregatedCharmDescriptionProvider;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.magic.description.model.MagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.model.RegisteredMagicDescriptionProviderFactory;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import java.util.Collection;

public class CharmDescriptionProviderExtractor {

  public static MagicDescriptionProvider CreateFor(ApplicationModel model, Environment environment) {
    AggregatedCharmDescriptionProvider provider = new AggregatedCharmDescriptionProvider(environment);
    Collection<MagicDescriptionProviderFactory> factories = findFactories(environment);
    for (MagicDescriptionProviderFactory factory : factories) {
      provider.addProvider(factory.create(model));
    }
    return provider;
  }

  private static Collection<MagicDescriptionProviderFactory> findFactories(Environment environment) {
    return environment.getObjectFactory().instantiateAll(RegisteredMagicDescriptionProviderFactory.class);
  }

}
