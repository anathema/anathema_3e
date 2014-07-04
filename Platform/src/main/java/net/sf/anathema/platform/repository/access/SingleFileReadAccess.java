package net.sf.anathema.platform.repository.access;

import net.sf.anathema.library.exception.PersistenceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SingleFileReadAccess implements RepositoryReadAccess {

  private final File repositoryFile;

  public SingleFileReadAccess(File repositoryFile) {
    this.repositoryFile = repositoryFile;
  }

  @Override
  public InputStream openMainInputStream() {
    try {
      return new FileInputStream(repositoryFile);
    } catch (FileNotFoundException e) {
      throw new PersistenceException(e);
    }
  }

  @Override
  public InputStream openSubInputStream(String streamID) {
    throw new UnsupportedOperationException();
  }

  @Override
  public File[] getFiles() {
    return new File[]{repositoryFile};
  }
}