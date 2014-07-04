package net.sf.anathema.platform.environment.dependencies;

import net.sf.anathema.library.io.Filenames;
import net.sf.anathema.library.resources.ResourceFile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ExternalResourceFile implements ResourceFile {
  private final File file;

  public ExternalResourceFile(File file) {
    this.file = file;
  }

  @Override
  public URL getURL() {
    try {
      return file.toURI().toURL();
    } catch (MalformedURLException e) {
      throw new RuntimeException("Could not load file " + file.getName());
    }
  }

  @Override
  public String getFileName() {
    return Filenames.separatorsToUnix(file.getAbsolutePath());
  }

  @Override
  public String toString() {
    return "External: " + getFileName();
  }
}