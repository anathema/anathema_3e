package net.sf.anathema.framework.messaging;

import net.sf.anathema.library.message.Message;
import net.sf.anathema.library.message.MessageType;

public interface Messaging {

  MessageToken addPermanentMessage(MessageType messageType, String messagePattern, Object... arguments);

  MessageToken addTemporaryMessage(MessageType messageType, String messagePattern, Object... arguments);

  MessageToken addMessage(Message message);

  MessageToken obtainInitialToken();

}