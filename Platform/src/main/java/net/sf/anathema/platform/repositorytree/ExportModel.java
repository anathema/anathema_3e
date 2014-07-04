package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.platform.repository.PrintNameFile;
import net.sf.anathema.platform.repository.access.RepositoryFileAccess;

public interface ExportModel {
  PrintNameFile[] getPrintNameFilesInSelection();

  RepositoryFileAccess getFileAccess(PrintNameFile printNameFile);
}
