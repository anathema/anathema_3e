package net.sf.anathema.hero.application.environment;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.initialization.Registry;
import net.sf.anathema.platform.frame.AnathemaExtension;
import net.sf.anathema.platform.frame.ApplicationModel;

public class HeroEnvironmentFetcher {

  public static HeroEnvironment fetch(ApplicationModel model) {
    Registry<String, AnathemaExtension> registry = model.getExtensionRegistry();
    HeroEnvironmentExtension genericsExtension = (HeroEnvironmentExtension) registry.get(HeroEnvironmentExtension.ID);
    return genericsExtension.getEnvironment();
  }
}
