package net.sf.anathema.platform.messaging;

import net.sf.anathema.library.message.Messaging;

public interface CategorizedMessaging extends Messaging {
  MessageCategory Default_Message_Category = new MessageCategory("Default");

  void activateCategory(MessageCategory category);
}