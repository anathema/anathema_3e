package net.sf.anathema.platform.messaging;

import net.sf.anathema.library.message.Messaging;

public interface CategorizedMessaging extends Messaging {
  void activateCategory(MessageCategory category);
}