package net.sf.anathema.framework.repository.access;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.exception.PersistenceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MultiFileReadAccess implements RepositoryReadAccess {

  private final File itemFolder;
  private final String mainFileName;
  private final String extension;

  public MultiFileReadAccess(File itemFolder, String mainFileName, String extension) {
    Preconditions.checkArgument(itemFolder.isDirectory());
    this.itemFolder = itemFolder;
    this.mainFileName = mainFileName;
    this.extension = extension;
  }

  @Override
  public InputStream openMainInputStream() {
    try {
      return openInputStream(mainFileName);
    }
    catch (FileNotFoundException e) {
      throw new PersistenceException(e);
    }
  }

  @Override
  public InputStream openSubInputStream(String streamID) {
    try {
      return openInputStream(streamID);
    }
    catch (FileNotFoundException e) {
      throw new PersistenceException(e);
    }
  }

  private InputStream openInputStream(String id) throws FileNotFoundException {
    File file = new File(itemFolder, id + extension);
    if (!file.exists()) {
      return null;
    }
    return new FileInputStream(file);
  }

  @Override
  public File[] getFiles() {
    return itemFolder.listFiles();
  }
}