package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.io.ConfigurableFileProvider;
import net.sf.anathema.platform.repository.PrintNameFile;
import net.sf.anathema.platform.repository.Repository;
import net.sf.anathema.platform.repository.access.RepositoryFileAccess;
import net.sf.anathema.platform.repository.access.RepositoryReadAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RepositoryFileAccessFactory {
  private final Repository repository;

  public RepositoryFileAccessFactory(Repository repository) {
    this.repository = repository;
  }

  public RepositoryFileAccess getFileAccess(PrintNameFile printNameFile) {
    ConfigurableFileProvider provider = new ConfigurableFileProvider();
    provider.setFile(printNameFile.getFile());
    RepositoryReadAccess access = repository.openReadAccess(printNameFile.getItemType(), provider);
    return new RepositoryFileAccess() {
      @Override
      public File[] getFiles() {
        return access.getFiles();
      }

      @Override
      public InputStream openInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
      }
    };
  }
}