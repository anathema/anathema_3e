package net.sf.anathema.platform.repository;

import net.sf.anathema.library.exception.PersistenceException;

import java.io.File;
import java.io.IOException;

public class RepositoryFolderCreator {

  private final IStringResolver pathResolver;
  private final FileSystem fileSystem;

  public RepositoryFolderCreator(FileSystem fileSystem, IStringResolver pathResolver) {
    this.fileSystem = fileSystem;
    this.pathResolver = pathResolver;
  }

  public File createRepositoryFolder() {
    File file = new File(pathResolver.resolve());
    if (!fileSystem.exists(file)) {
      try {
        fileSystem.createFolder(file);
      }
      catch (IOException e) {
        throw new PersistenceException(e);
      }
    }
    if (!fileSystem.canRead(file) || !fileSystem.canWrite(file)) {
      throw new PersistenceException("Read/Write error on repository at " + file.getAbsolutePath());
    }
    return file;
  }
}