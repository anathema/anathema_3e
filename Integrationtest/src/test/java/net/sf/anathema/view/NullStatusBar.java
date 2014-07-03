package net.sf.anathema.view;

import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.message.Message;

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