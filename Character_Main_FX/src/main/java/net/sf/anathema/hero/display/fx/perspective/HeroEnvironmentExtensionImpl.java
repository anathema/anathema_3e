package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.DataSetInitializer;
import net.sf.anathema.hero.application.environment.HeroEnvironmentExtension;
import net.sf.anathema.hero.application.environment.HeroEnvironmentImpl;
import net.sf.anathema.hero.application.template.CharacterTemplateInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetProvider;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.AnathemaExtension;
import net.sf.anathema.platform.frame.ApplicationModel;

public class HeroEnvironmentExtensionImpl implements HeroEnvironmentExtension, AnathemaExtension {

  private HeroEnvironment heroEnvironment;

  @Override
  public HeroEnvironment getEnvironment() {
    return heroEnvironment;
  }

  public void initialize(ApplicationModel model, Environment environment) {
    ExtensibleDataSetProvider dataSetProvider = new DataSetInitializer(environment, environment.getObjectFactory()).initializeExtensibleResources();
    this.heroEnvironment = new HeroEnvironmentImpl(model, environment, dataSetProvider);
    new CharacterTemplateInitializer(this.heroEnvironment).addHeroSplats();
  }
}