package net.sf.anathema.hero.framework;

import net.sf.anathema.library.initialization.Registry;
import net.sf.anathema.platform.frame.AnathemaExtension;
import net.sf.anathema.platform.frame.ApplicationModel;

public class HeroEnvironmentExtractor {

  public static HeroEnvironment getGenerics(ApplicationModel model) {
    Registry<String, AnathemaExtension> registry = model.getExtensionRegistry();
    HeroEnvironmentExtension genericsExtension = (HeroEnvironmentExtension) registry.get(HeroEnvironmentExtension.ID);
    return genericsExtension.getEnvironment();
  }
}
