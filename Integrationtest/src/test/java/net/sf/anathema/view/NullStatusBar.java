package net.sf.anathema.view;

import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.message.Message;
import net.sf.anathema.platform.messaging.StatusBar;

import java.util.Collection;

public class NullStatusBar implements StatusBar {
  @Override
  public void setLatestMessage(Message message) {
    //nothing to do
  }

  @Override
  public void whenAllMessagesAreRequested(Command command) {
    //nothing to do
  }

  @Override
  public void showMessages(Collection<Message> messages) {
    //nothing to do
  }
}