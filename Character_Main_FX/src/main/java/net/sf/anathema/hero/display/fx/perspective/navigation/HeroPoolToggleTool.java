package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import net.sf.anathema.library.fx.tool.DeselectToggleButtonAndProcess;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.view.Style;

public class HeroPoolToggleTool extends HeroPoolTool implements ToggleTool {

  private ToggleButton button;

  public HeroPoolToggleTool() {
    super(new ToggleButton());
    this.button = (ToggleButton) super.getNode();
    button.getStyleClass().add("clean");
  }

  @Override
  public void setCommand(Command command) {
    super.setCommand(new DeselectToggleButtonAndProcess(this, command));
  }

  @Override
  public void setStyle(Style style) {
    throw new UnsupportedOperationException("Expected to look like all other Hero-Pool-Buttons");
  }

  @Override
  public void select() {
    button.setSelected(true);
  }

  @Override
  public void deselect() {
    button.setSelected(false);
  }

  public void setToggleGroup(ToggleGroup toggleGroup) {
    button.setToggleGroup(toggleGroup);
  }
}
