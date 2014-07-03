package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.Message;

public class NullToken implements MessageToken {
  private Messaging messaging;

  public NullToken(Messaging messaging) {
    this.messaging = messaging;
  }

  @Override
  public void replaceMessage(Message message) {
    messaging.addMessage(message);
  }
}
