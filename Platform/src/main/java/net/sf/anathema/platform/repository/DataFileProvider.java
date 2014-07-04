package net.sf.anathema.platform.repository;

import java.nio.file.Path;

public interface DataFileProvider {

  Path getDataBaseDirectory(String subfolderName);
}