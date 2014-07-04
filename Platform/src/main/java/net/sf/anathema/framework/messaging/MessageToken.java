package net.sf.anathema.framework.messaging;

import net.sf.anathema.library.message.Message;
import net.sf.anathema.library.message.MessageType;

public interface MessageToken {
  void replaceMessage(Message message);

  void replaceMessage(MessageType type, String pattern, String... arguments);
}
