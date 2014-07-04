package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.io.InputOutput;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.access.RepositoryWriteAccess;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RepositoryImportHandler {

  private final RepositoryWriteAccess access;
  private final String oldId;
  private final String newId;

  public RepositoryImportHandler(IRepositoryTreeModel model, IItemType type, String oldId) {
    this.newId = model.createUniqueId(type, oldId);
    this.access = model.getWriteAccess(type, newId);
    this.oldId = oldId;
  }

  public void importStream(String mainFilePath, InputStream inputStream,
                           String entryName) throws IOException {
    if (entryName.equals(mainFilePath)) {
      writeMainFileWithLegalId(inputStream);
    } else {
      writeSubFile(inputStream, entryName);
    }
  }

  private void writeMainFileWithLegalId(InputStream inputStream) throws IOException {
    try (InputStream modifiedInput = new ImportIdReplacer(oldId, newId).createStreamWithLegalId(inputStream)) {
      writeMainFile(modifiedInput);
    }
  }

  private void writeMainFile(InputStream modifiedInput) throws IOException {
    try (OutputStream outputStream = access.createMainOutputStream()) {
      importStreamToRepository(modifiedInput, outputStream);
    }
  }

  private void writeSubFile(InputStream inputStream, String entryName) throws IOException {
    String unextendedFileName = entryName.substring(entryName.lastIndexOf(File.separator) + 1,
            entryName.lastIndexOf("."));
    try (OutputStream outputStream = access.createSubOutputStream(unextendedFileName)) {
      importStreamToRepository(inputStream, outputStream);
    }
  }

  private void importStreamToRepository(InputStream importStream, OutputStream repositoryStream) throws IOException {
    InputOutput.copy(importStream, repositoryStream);
  }

  public String getNewId() {
    return newId;
  }
}