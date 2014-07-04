package net.sf.anathema.platform.repository;

import net.sf.anathema.library.io.InputOutput;

import java.io.File;
import java.io.IOException;

public class IOFileSystem implements FileSystem {

  @Override
  public void createFolder(File folder) throws IOException {
    InputOutput.forceMkdir(folder);
  }

  @Override
  public boolean canWrite(File file) {
    return file.canWrite();
  }

  @Override
  public boolean canRead(File file) {
    return file.canRead();
  }

  @Override
  public boolean exists(File file) {
    return file.exists();
  }
}