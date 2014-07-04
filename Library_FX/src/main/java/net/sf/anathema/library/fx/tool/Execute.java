package net.sf.anathema.library.fx.tool;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.sf.anathema.library.interaction.model.Command;

public class Execute implements EventHandler<ActionEvent> {
  private final Command command;

  public Execute(Command command) {
    this.command = command;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    command.execute();
  }
}