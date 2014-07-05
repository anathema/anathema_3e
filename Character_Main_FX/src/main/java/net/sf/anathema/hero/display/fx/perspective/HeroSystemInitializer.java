package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.environment.HeroEnvironmentExtension;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

public class HeroSystemInitializer {
  private final ApplicationModel model;
  private final Environment environment;

  public HeroSystemInitializer(ApplicationModel model, Environment environment) {
    this.model = model;
    this.environment = environment;
  }

  public void initializeCharacterSystem() {
    HeroEnvironmentExtensionImpl extension = new HeroEnvironmentExtensionImpl();
    extension.initialize(model, environment);
    model.getExtensionRegistry().register(HeroEnvironmentExtension.ID, extension);
  }
}