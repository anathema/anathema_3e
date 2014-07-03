package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;

public class NullMessaging implements Messaging {
  @Override
  public MessageToken addPermanentMessage(MessageType messageType, String pattern, Object... arguments) {
    return new NullToken(this);
  }

  @Override
  public MessageToken addTemporaryMessage(MessageType messageType, String messagePattern, Object... arguments) {
    return new NullToken(this);
  }

  @Override
  public MessageToken addMessage(Message message) {
    return new NullToken(this);
  }

  @Override
  public MessageToken obtainInitialToken() {
    return new NullToken(this);
  }
}