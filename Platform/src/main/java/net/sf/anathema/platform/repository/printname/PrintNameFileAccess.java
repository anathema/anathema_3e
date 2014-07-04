package net.sf.anathema.platform.repository.printname;

import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.PrintNameFile;

import java.util.Collection;

public interface PrintNameFileAccess {

  Collection<PrintNameFile> collectAllPrintNameFiles(IItemType type);

  PrintNameFile getPrintNameFile(IItemType itemType, String repositoryId);
}