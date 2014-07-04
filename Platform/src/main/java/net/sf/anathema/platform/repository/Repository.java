package net.sf.anathema.platform.repository;

import net.sf.anathema.library.io.DataFileProvider;
import net.sf.anathema.library.io.IFileProvider;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.access.RepositoryReadAccess;
import net.sf.anathema.platform.repository.access.RepositoryWriteAccess;
import net.sf.anathema.platform.repository.printname.PrintNameFileAccess;
import net.sf.anathema.platform.repository.printname.ReferenceAccess;
import net.sf.anathema.platform.repository.printname.ReferenceBuilder;

public interface Repository extends DataFileProvider {

  RepositoryWriteAccess createWriteAccess(IItemType type, String id);

  RepositoryReadAccess openReadAccess(IItemType type, IFileProvider provider);

  RepositoryReadAccess openReadAccess(IItemType type, String id);

  boolean knowsItem(IItemType type, String id);

  PrintNameFileAccess getPrintNameFileAccess();

  <R> ReferenceAccess<R> createReferenceAccess(IItemType type, ReferenceBuilder<R> builder);

  String getRepositoryPath();

  void deleteAssociatedItem(PrintNameFile userObject);

  String createUniqueRepositoryId(RepositoryIdData repositoryLocation);

  IRepositoryFileResolver getRepositoryFileResolver();
}