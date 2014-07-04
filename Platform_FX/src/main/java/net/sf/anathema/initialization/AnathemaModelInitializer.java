package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.configuration.PreferencesBasedRepositoryLocation;
import net.sf.anathema.framework.configuration.RepositoryPreference;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.platform.environment.Environment;

import java.io.File;

public class AnathemaModelInitializer {

  public IApplicationModel initializeModel(Environment environment) {
    return createModel(environment);
  }

  private ApplicationModel createModel(Environment environment) {
    try {
      return new ApplicationModel(createRepositoryFolder(environment), environment);
    } catch (PersistenceException e) {
      throw new InitializationException("Failed to create repository folder.\nPlease check read/write permissions.", e);
    }
  }

  private File createRepositoryFolder(Environment environment) {
    IOFileSystemAbstraction fileSystem = new IOFileSystemAbstraction();
    RepositoryPreference preferences = new PreferencesBasedRepositoryLocation(environment);
    RepositoryLocationResolver repositoryLocationResolver = new RepositoryLocationResolver(preferences);
    return new RepositoryFolderCreator(fileSystem, repositoryLocationResolver).createRepositoryFolder();
  }
}