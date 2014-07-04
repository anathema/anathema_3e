package net.sf.anathema.platform.fx.updatecheck;

import net.sf.anathema.platform.updatecheck.UpdateState;
import org.jmock.example.announcer.Announcer;

import java.util.function.Consumer;

public class UpdateModel {
  private UpdateState state;
  private Announcer<Consumer> announcer = Announcer.to(Consumer.class);

  public void setState(UpdateState state) {
    if (this.state == state) {
      return;
    }
    this.state = state;
    announcer.announce().accept(state);
  }

  public void whenStateChanges(Consumer<UpdateState> listener) {
    announcer.addListener(listener);
  }
}