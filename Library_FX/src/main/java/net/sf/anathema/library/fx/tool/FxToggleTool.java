package net.sf.anathema.library.fx.tool;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.view.Style;

public class FxToggleTool extends FxBaseTool implements ToggleTool {

  public static FxToggleTool create() {
    ImageView mainIcon = new ImageView();
    ImageView miniIcon = new ImageView();
    ToggleButton button = new ToggleButton("", mainIcon);
    return new FxToggleTool(button, miniIcon, new AdjustSize(button), new SetImage(mainIcon));
  }

  private final ToggleButton button;
  private Style style = new Style("");

  public FxToggleTool(final ToggleButton button, ImageView miniIcon, ImageClosure... onLoad) {
    super(button, miniIcon, onLoad);
    this.button = button;
  }


  @Override
  public void setCommand(Command command) {
    Command deselectAndProcess = new DeselectToggleButtonAndProcess(command);
    super.setCommand(deselectAndProcess);
  }

  @Override
  public void select() {
    button.setSelected(true);
  }

  @Override
  public void deselect() {
    button.setSelected(false);
  }

  private void setStyleClass(String styleClass) {
    button.getStyleClass().setAll(styleClass);
  }

  public void registerWithGroup(ToggleGroup group) {
    group.getToggles().add(button);
  }

  @Override
  public void setStyle(Style style) {
    button.getStyleClass().remove(this.style.style);
    button.getStyleClass().add(style.style);
    this.style = style;
  }

  private class DeselectToggleButtonAndProcess implements Command {
    private Command command;

    public DeselectToggleButtonAndProcess(Command command) {
      this.command = command;
    }

    @Override
    public void execute() {
      deselect();
      command.execute();
    }
  }
}