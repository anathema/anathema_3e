package net.sf.anathema.framework.repository.access;

import net.sf.anathema.lib.exception.PersistenceException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class SingleFileWriteAccess implements RepositoryWriteAccess {

  private final File repositoryFile;

  public SingleFileWriteAccess(File repositoryfile) {
    this.repositoryFile = repositoryfile;
  }

  @Override
  public OutputStream createMainOutputStream() {
    try {
      return new FileOutputStream(repositoryFile);
    }
    catch (FileNotFoundException e) {
      throw new PersistenceException(e);
    }
  }

  @Override
  public OutputStream createSubOutputStream(String streamID) {
    throw new UnsupportedOperationException();
  }
}