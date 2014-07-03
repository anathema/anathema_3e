package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;

public interface MessageToken {
  void replaceMessage(Message message);

  void replaceMessage(MessageType type, String pattern, String... arguments);
}
