package net.sf.anathema.platform.fx.environment;

import javafx.stage.Stage;
import net.sf.anathema.library.interaction.AcceleratorMap;
import net.sf.anathema.library.interaction.ProxyAcceleratorMap;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.Hotkey;
import net.sf.anathema.library.io.FileChooserConfiguration;
import net.sf.anathema.library.io.FileExtension;
import net.sf.anathema.library.io.SingleFileChooser;
import net.sf.anathema.platform.fx.initialization.ProxyFileChooser;
import org.controlsfx.dialog.Dialog;

import java.nio.file.Path;

import static org.controlsfx.dialog.DialogStyle.NATIVE;

public class FxUiEnvironment implements UiEnvironment {

  private final ProxyFileChooser chooser = new ProxyFileChooser();
  private final ProxyAcceleratorMap map = new ProxyAcceleratorMap();
  private Stage stage;

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

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @Override
  public Dialog createDialog(String title) {
    return new Dialog(stage, title, false, NATIVE);
  }
}
