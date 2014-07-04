package net.sf.anathema.framework.messaging;

import net.sf.anathema.library.message.Message;
import net.sf.anathema.library.message.MessageType;

public class NullToken implements MessageToken {
  private Messaging messaging;

  public NullToken(Messaging messaging) {
    this.messaging = messaging;
  }

  @Override
  public void replaceMessage(Message message) {
    messaging.addMessage(message);
  }

  @Override
  public void replaceMessage(MessageType type, String pattern, String... arguments) {
    messaging.addTemporaryMessage(type, pattern, arguments);
  }
}
