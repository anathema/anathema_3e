package net.sf.anathema.platform.repository.printname;

import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.PrintNameFile;

import java.io.File;
import java.io.IOException;

public interface PrintNameFileReader {

  PrintNameFile readPrintName(File file, IItemType itemType) throws IOException;
}