package net.sf.anathema.platform.fx.environment;

import javafx.scene.control.Dialog;
import net.sf.anathema.library.interaction.AcceleratorMap;
import net.sf.anathema.library.interaction.ProxyAcceleratorMap;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.Hotkey;
import net.sf.anathema.library.io.FileChooserConfiguration;
import net.sf.anathema.library.io.FileExtension;
import net.sf.anathema.library.io.SingleFileChooser;
import net.sf.anathema.platform.fx.initialization.ProxyFileChooser;

import java.nio.file.Path;

public class FxUiEnvironment implements UiEnvironment {

  private final ProxyFileChooser chooser = new ProxyFileChooser();
  private final ProxyAcceleratorMap map = new ProxyAcceleratorMap();

  @Override
  public void register(Hotkey hotkey, Command command) {
    map.register(hotkey, command);
  }

  @Override
  public Path selectSaveFile(FileChooserConfiguration configuration) {
    return chooser.selectSaveFile(configuration);
  }

  @Override
  public Path selectLoadFile(FileExtension extension) {
    return chooser.selectLoadFile(extension);
  }

  public void setFileChooser(SingleFileChooser chooser) {
    this.chooser.setDelegate(chooser);
  }

  public void setAcceleratorMap(AcceleratorMap map) {
    this.map.setActualMap(map);
  }

  @Override
  public Dialog createDialog(String title) {
    Dialog dialog = new Dialog();
    dialog.setTitle(title);
    return dialog;
  }
}
