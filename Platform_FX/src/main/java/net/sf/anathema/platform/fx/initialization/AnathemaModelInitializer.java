package net.sf.anathema.platform.fx.initialization;

import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.frame.ApplicationModelImpl;
import net.sf.anathema.platform.repository.IOFileSystem;
import net.sf.anathema.platform.repository.PreferencesBasedRepositoryLocation;
import net.sf.anathema.platform.repository.RepositoryFolderCreator;
import net.sf.anathema.platform.repository.RepositoryLocationResolver;
import net.sf.anathema.platform.repository.RepositoryPreference;

import java.io.File;

public class AnathemaModelInitializer {

  public ApplicationModel initializeModel(Environment environment) {
    return createModel(environment);
  }

  private ApplicationModelImpl createModel(Environment environment) {
    try {
      return new ApplicationModelImpl(createRepositoryFolder(environment), environment);
    } catch (PersistenceException e) {
      throw new InitializationException("Failed to create repository folder.\nPlease check read/write permissions.", e);
    }
  }

  private File createRepositoryFolder(Environment environment) {
    IOFileSystem fileSystem = new IOFileSystem();
    RepositoryPreference preferences = new PreferencesBasedRepositoryLocation(environment);
    RepositoryLocationResolver repositoryLocationResolver = new RepositoryLocationResolver(preferences);
    return new RepositoryFolderCreator(fileSystem, repositoryLocationResolver).createRepositoryFolder();
  }
}