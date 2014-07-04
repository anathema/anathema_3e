package net.sf.anathema.platform.repository;

import java.io.File;
import java.io.IOException;

public interface FileSystem {

  void createFolder(File folder) throws IOException;

  boolean canWrite(File file);

  boolean canRead(File file);

  boolean exists(File file);
}