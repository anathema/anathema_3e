package net.sf.anathema.library.fx.tool;

import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.ToggleTool;

public class DeselectToggleButtonAndProcess implements Command {
  private ToggleTool tool;
  private Command command;

  public DeselectToggleButtonAndProcess(ToggleTool tool, Command command) {
    this.tool = tool;
    this.command = command;
  }

  @Override
  public void execute() {
    tool.deselect();
    command.execute();
  }
}
