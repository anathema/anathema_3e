package net.sf.anathema.library.io;

import java.nio.file.Path;

public interface DataFileProvider {

  Path getDataBaseDirectory(String subfolderName);
}