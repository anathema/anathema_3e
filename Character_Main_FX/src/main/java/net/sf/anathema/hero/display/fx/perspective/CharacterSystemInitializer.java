package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

public class CharacterSystemInitializer {
  private final ApplicationModel model;
  private final Environment environment;

  public CharacterSystemInitializer(ApplicationModel model, Environment environment) {
    this.model = model;
    this.environment = environment;
  }

  public void initializeCharacterSystem() {
      CharacterGenericsExtension extension = new CharacterGenericsExtension();
      extension.initialize(model.getRepository(), environment, environment);
      model.getExtensionRegistry().register(CharacterGenericsExtension.ID, extension);
  }
}