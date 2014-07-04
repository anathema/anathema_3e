package net.sf.anathema.platform.messaging;

import net.sf.anathema.library.message.MessageType;

public interface IMessageData {

  String getMessage();

  MessageType getType();
}