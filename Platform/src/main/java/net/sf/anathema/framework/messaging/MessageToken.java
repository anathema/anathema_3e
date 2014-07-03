package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.Message;

public interface MessageToken {
  void replaceMessage(Message message);
}
