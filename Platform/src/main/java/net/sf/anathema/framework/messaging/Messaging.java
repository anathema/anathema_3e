package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;

public interface Messaging {

  MessageToken addPermanentMessage(MessageType messageType, String messagePattern, Object... arguments);

  MessageToken addTemporaryMessage(MessageType messageType, String messagePattern, Object... arguments);

  MessageToken addMessage(Message message);

  MessageToken obtainInitialToken();

}