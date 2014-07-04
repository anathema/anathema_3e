package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.Message;
import net.sf.anathema.library.event.ChangeListener;

import java.util.Collection;

public interface MessageContainer {

  void addChangeListener(ChangeListener listener);

  Message getLatestMessage();

  Collection<Message> getPermanentMessages();

  boolean hasMessages();
}