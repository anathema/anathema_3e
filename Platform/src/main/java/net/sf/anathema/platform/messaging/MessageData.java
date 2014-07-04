package net.sf.anathema.platform.messaging;

import net.sf.anathema.library.message.MessageType;

public class MessageData implements IMessageData {

  private final String message;
  private final MessageType type;

  public MessageData(String message, MessageType type) {
    this.message = message;
    this.type = type;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public MessageType getType() {
    return type;
  }
}
