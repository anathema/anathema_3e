package net.sf.anathema.library.fx.tool;

import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import net.sf.anathema.library.fx.view.FxComponent;
import net.sf.anathema.library.interaction.AcceleratorMap;
import net.sf.anathema.library.interaction.ProxyAcceleratorMap;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.CommandProxy;
import net.sf.anathema.library.interaction.model.Hotkey;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.RelativePath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FxBaseTool implements Tool, FxComponent {
  private final ButtonBase button;
  private final ImageView overlay;
  private final List<ImageClosure> onLoad = new ArrayList<>();
  private final ProxyAcceleratorMap acceleratorMap = new ProxyAcceleratorMap();
  private final CommandProxy command = new CommandProxy();

  public FxBaseTool(final ButtonBase button, ImageView overlay, ImageClosure... actionsOnLoad) {
    this.button = button;
    this.overlay = overlay;
    Collections.addAll(onLoad, actionsOnLoad);
    button.setOnAction(new Execute(command));
  }

  @Override
  public void setIcon(final RelativePath relativePath) {
    ImageContainer image = new LoadImage(relativePath).run();
    for (ImageClosure action : onLoad) {
      action.run(image);
    }
  }


  @Override
  public void setOverlay(final RelativePath relativePath) {
    ImageContainer image = new LoadImage(relativePath).run();
    new SetImage(overlay).run(image);
  }

  @Override
  public void setTooltip(final String text) {
    button.setTooltip(new Tooltip(text));
  }

  @Override
  public void setText(final String text) {
    button.setText(text);
  }

  @Override
  public void enable() {
    button.setDisable(false);
  }

  @Override
  public void disable() {
    button.setDisable(true);
  }

  @Override
  public void setCommand(final Command command) {
    this.command.setDelegate(command);
  }

  @Override
  public void setHotkey(Hotkey key) {
    acceleratorMap.register(key, () -> {
      if (button.isDisabled()) {
        return;
      }
      command.execute();
    });
  }

  public void registerHotkeyIn(AcceleratorMap actualMap) {
    acceleratorMap.setActualMap(actualMap);
  }

  @Override
  public Node getNode() {
    return button;
  }
}