package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.template.CharacterTemplateInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetProvider;
import net.sf.anathema.hero.framework.DataSetInitializer;
import net.sf.anathema.hero.framework.HeroEnvironmentExtension;
import net.sf.anathema.hero.framework.HeroEnvironmentImpl;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.io.DataFileProvider;
import net.sf.anathema.library.resources.ResourceFileLoader;
import net.sf.anathema.platform.frame.AnathemaExtension;

public class CharacterGenericsExtension implements HeroEnvironmentExtension, AnathemaExtension {

  private HeroEnvironment environment;

  @Override
  public HeroEnvironment getEnvironment() {
    return environment;
  }

  public void initialize(DataFileProvider dataFileProvider, ObjectFactory objectFactory, ResourceFileLoader loader) {
    ExtensibleDataSetProvider dataSetProvider = new DataSetInitializer(loader, objectFactory).initializeExtensibleResources();
    this.environment = new HeroEnvironmentImpl(dataFileProvider, objectFactory, dataSetProvider);
    new CharacterTemplateInitializer(environment).addCharacterTemplates();
  }
}