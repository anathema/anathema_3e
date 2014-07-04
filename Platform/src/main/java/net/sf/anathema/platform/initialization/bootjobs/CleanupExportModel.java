package net.sf.anathema.platform.initialization.bootjobs;

import com.google.common.collect.Lists;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.item.ItemTypeCollection;
import net.sf.anathema.platform.repository.PrintNameFile;
import net.sf.anathema.platform.repository.Repository;
import net.sf.anathema.platform.repository.access.RepositoryFileAccess;
import net.sf.anathema.platform.repositorytree.ExportModel;
import net.sf.anathema.platform.repositorytree.RepositoryFileAccessFactory;

import java.util.List;

public class CleanupExportModel implements ExportModel {
  private final ItemTypeCollection allItemTypes;
  private final Repository repository;
  private final RepositoryFileAccessFactory accessFactory;

  public CleanupExportModel(ItemTypeCollection allItemTypes, Repository repository) {
    this.allItemTypes = allItemTypes;
    this.repository = repository;
    this.accessFactory = new RepositoryFileAccessFactory(repository);
  }

  @Override
  public PrintNameFile[] getPrintNameFilesInSelection() {
    List<PrintNameFile> files = Lists.newArrayList();
    for (IItemType itemType : allItemTypes) {
      files.addAll(repository.getPrintNameFileAccess().collectAllPrintNameFiles(itemType));
    }
    return files.toArray(new PrintNameFile[files.size()]);
  }

  @Override
  public RepositoryFileAccess getFileAccess(PrintNameFile printNameFile) {
    return accessFactory.getFileAccess(printNameFile);
  }
}