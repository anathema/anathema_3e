package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.message.Message;

import java.util.Collection;

public interface MessageContainer {

  void addChangeListener(ChangeListener listener);

  Message getLatestMessage();

  Collection<Message> getPermanentMessages();

  boolean hasMessages();
}