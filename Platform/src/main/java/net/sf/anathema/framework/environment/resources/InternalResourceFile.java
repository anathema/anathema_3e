package net.sf.anathema.framework.environment.resources;

import net.sf.anathema.library.resources.ResourceFile;

import java.net.URL;

public class InternalResourceFile implements ResourceFile {
  private final String fileName;
  private final ClassLoader loader;

  public InternalResourceFile(String fileName, ClassLoader loader) {
    this.fileName = fileName;
    this.loader = loader;
  }

  @Override
  public URL getURL() {
    return loader.getResource(fileName);
  }

  @Override
  public String getFileName() {
    return fileName;
  }

  @Override
  public String toString() {
    return "External: " + getFileName();
  }
}