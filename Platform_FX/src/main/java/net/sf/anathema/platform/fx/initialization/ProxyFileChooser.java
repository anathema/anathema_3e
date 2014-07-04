package net.sf.anathema.platform.fx.initialization;

import net.sf.anathema.library.io.FileChooserConfiguration;
import net.sf.anathema.library.io.FileExtension;
import net.sf.anathema.library.io.SingleFileChooser;

import java.nio.file.Path;

public class ProxyFileChooser implements SingleFileChooser {

  private SingleFileChooser delegate;

  public void setDelegate(SingleFileChooser delegate) {
    this.delegate = delegate;
  }

  @Override
  public Path selectSaveFile(FileChooserConfiguration configuration) {
    return delegate.selectSaveFile(configuration);
  }

  @Override
  public Path selectLoadFile(FileExtension extension) {
    return delegate.selectLoadFile(extension);
  }
}
