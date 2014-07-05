package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.magic.description.model.AggregatedCharmDescriptionProvider;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.magic.description.model.MagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.model.RegisteredMagicDescriptionProviderFactory;

import java.util.Collection;

public class CharmDescriptionProviderExtractor {

  public static MagicDescriptionProvider CreateFor(HeroEnvironment environment) {
    AggregatedCharmDescriptionProvider provider = new AggregatedCharmDescriptionProvider(environment.getResources());
    Collection<MagicDescriptionProviderFactory> factories = findFactories(environment);
    for (MagicDescriptionProviderFactory factory : factories) {
      provider.addProvider(factory.create(environment));
    }
    return provider;
  }

  private static Collection<MagicDescriptionProviderFactory> findFactories(HeroEnvironment environment) {
    ObjectFactory objectFactory = environment.getObjectFactory();
    return objectFactory.instantiateAll(RegisteredMagicDescriptionProviderFactory.class);
  }

}
