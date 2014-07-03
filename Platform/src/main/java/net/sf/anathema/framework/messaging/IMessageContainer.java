package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.message.Message;

import java.util.List;

public interface IMessageContainer {

  void addChangeListener(ChangeListener listener);

  Message getLatestMessage();

  List<Message> getAllMessages();
}