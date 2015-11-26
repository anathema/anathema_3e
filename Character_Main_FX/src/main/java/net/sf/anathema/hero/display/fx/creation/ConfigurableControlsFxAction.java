package net.sf.anathema.hero.display.fx.creation;

import javafx.event.ActionEvent;
import net.sf.anathema.library.interaction.model.Command;
import org.controlsfx.control.action.Action;

import java.util.function.Consumer;

public class ConfigurableControlsFxAction extends Action {
  private final CommandHandler handler;

  public ConfigurableControlsFxAction(String text) {
    super(text, new CommandHandler());
    this.handler = (CommandHandler) getEventHandler();
  }

  public void setCommand(Command command) {
    this.handler.setCommand(command);
  }

  public static class CommandHandler implements Consumer<ActionEvent>{

    private Command command;

    public void setCommand(Command command){
      this.command = command;
    }
    
    @Override
    public void accept(ActionEvent actionEvent) {
      command.execute();
    }
  }
}
