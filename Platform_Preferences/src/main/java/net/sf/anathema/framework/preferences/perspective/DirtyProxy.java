package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.platform.preferences.DirtyModel;
import org.jmock.example.announcer.Announcer;

public class DirtyProxy implements DirtyModel {

  private final Announcer<Command> announcer = Announcer.to(Command.class);

  public void whenDirtied(Command command) {
    announcer.addListener(command);
  }

  public void register(DirtyModel model) {
    model.whenDirtied(new Command() {
      @Override
      public void execute() {
        announcer.announce().execute();
      }
    });
  }
}
