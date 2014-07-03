package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageDuration;
import net.sf.anathema.lib.message.MessageType;

public interface Messaging {

  MessageToken addMessage(MessageType messageType, MessageDuration duration, String pattern, Object... arguments);

  MessageToken addMessage(MessageType messageType, String messagePattern, Object... arguments);

  MessageToken addMessage(Message message);

  MessageToken obtainInitialToken();

}