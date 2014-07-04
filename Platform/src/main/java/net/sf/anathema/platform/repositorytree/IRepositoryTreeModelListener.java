package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.platform.repository.PrintNameFile;

public interface IRepositoryTreeModelListener {

  void printNameFileAdded(PrintNameFile file);

  void printNameFileRemoved(PrintNameFile file);
}