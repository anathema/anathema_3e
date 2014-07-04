package net.sf.anathema.platform.repository;

import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.platform.preferences.CanonicalPathResolver;

import java.io.File;

import static java.text.MessageFormat.format;

public class RepositoryFolderWorker {

  public File createFolder(File folder) {
    try {
      create(folder);
      return folder;
    } catch (PersistenceException e) {
      String message = format("Could not create {0}:", folder.getAbsolutePath());
      throw new PersistenceException(message, e);
    }
  }

  private void create(File folder) {
    IOFileSystem fileSystem = new IOFileSystem();
    new RepositoryFolderCreator(fileSystem, new CanonicalPathResolver(folder)).createRepositoryFolder();
  }
}