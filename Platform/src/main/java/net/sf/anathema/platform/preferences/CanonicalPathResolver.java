package net.sf.anathema.platform.preferences;

import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.platform.repository.IStringResolver;

import java.io.File;
import java.io.IOException;

public class CanonicalPathResolver implements IStringResolver {

  private final File repositoryDirectory;

  public CanonicalPathResolver(File repositoryDirectory) {
    this.repositoryDirectory = repositoryDirectory;
  }

  @Override
  public String resolve() {
    try {
      return repositoryDirectory.getCanonicalPath();
    } catch (IOException e) {
      throw new PersistenceException("Could not resolve path " + repositoryDirectory.getAbsolutePath());
    }
  }
}
