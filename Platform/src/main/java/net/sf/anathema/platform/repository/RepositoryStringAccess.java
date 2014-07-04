package net.sf.anathema.platform.repository;

import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.io.InputOutput;
import net.sf.anathema.platform.item.IItemType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RepositoryStringAccess {
  private final Repository repository;
  private final IItemType itemType;

  public RepositoryStringAccess(Repository repository, IItemType itemType) {
    this.repository = repository;
    this.itemType = itemType;
  }

  public void write(String id, String representation) {
    try (OutputStream stream = repository.createWriteAccess(itemType, id).createMainOutputStream()) {
      stream.write(representation.getBytes());
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }

  public String read(String id) {
    try (InputStream stream = repository.openReadAccess(itemType, id).openMainInputStream()) {
      return InputOutput.toString(stream);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }
}