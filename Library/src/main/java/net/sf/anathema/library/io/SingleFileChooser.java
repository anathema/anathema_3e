package net.sf.anathema.library.io;

import java.nio.file.Path;

public interface SingleFileChooser {
  Path selectSaveFile(FileChooserConfiguration configuration);
  
  Path selectLoadFile(FileExtension extension);
}