package net.sf.anathema.framework.view.messaging;

import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.message.Message;

import java.util.Collection;

public interface StatusBar {

  void setLatestMessage(Message message);

  void whenAllMessagesAreRequested(Command command);

  void showMessages(Collection<Message> messages);
}