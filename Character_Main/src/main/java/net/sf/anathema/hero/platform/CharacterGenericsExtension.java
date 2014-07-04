package net.sf.anathema.hero.platform;

import net.sf.anathema.framework.extension.AnathemaExtension;
import net.sf.anathema.hero.framework.DataSetInitializer;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtension;
import net.sf.anathema.hero.framework.HeroEnvironmentImpl;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.hero.template.CharacterTemplateInitializer;
import net.sf.anathema.initialization.repository.DataFileProvider;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.resources.ResourceLoader;

public class CharacterGenericsExtension implements HeroEnvironmentExtension, AnathemaExtension {

  private HeroEnvironment environment;

  @Override
  public HeroEnvironment getEnvironment() {
    return environment;
  }

  public void initialize(DataFileProvider dataFileProvider, ObjectFactory objectFactory, ResourceLoader loader) {
    IExtensibleDataSetProvider dataSetProvider = new DataSetInitializer(loader, objectFactory).initializeExtensibleResources();
    this.environment = new HeroEnvironmentImpl(dataFileProvider, objectFactory, dataSetProvider);
    new CharacterTemplateInitializer(environment).addCharacterTemplates();
  }
}