package net.sf.anathema.hero.magic.display.presenter;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.magic.description.model.AggregatedMagicDescriptionProvider;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.magic.description.model.MagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.model.RegisteredMagicDescriptionProviderFactory;

import java.util.Collection;

public class MagicDescriptionProviderExtractor {

  public static MagicDescriptionProvider CreateFor(HeroEnvironment environment) {
    AggregatedMagicDescriptionProvider provider = new AggregatedMagicDescriptionProvider(environment.getResources());
    for (MagicDescriptionProviderFactory factory : findFactories(environment)) {
      provider.addProvider(factory.create(environment));
    }
    return provider;
  }

  private static Collection<MagicDescriptionProviderFactory> findFactories(HeroEnvironment environment) {
    ObjectFactory objectFactory = environment.getObjectFactory();
    return objectFactory.instantiateAll(RegisteredMagicDescriptionProviderFactory.class);
  }

}
