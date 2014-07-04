package net.sf.anathema.lib.io;

import org.apache.commons.io.FilenameUtils;

public class Filenames {
  public static String getBaseName(String name) {
    return FilenameUtils.getBaseName(name);
  }

  public static String separatorsToUnix(String path) {
    return FilenameUtils.separatorsToUnix(path);
  }

  public static String removeExtension(String fileName) {
    return FilenameUtils.removeExtension(fileName);
  }
}
