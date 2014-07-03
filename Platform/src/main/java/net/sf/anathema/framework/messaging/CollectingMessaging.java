package net.sf.anathema.framework.messaging;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageDuration;
import net.sf.anathema.lib.message.MessageType;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.lib.message.MessageDuration.Temporary;

public class CollectingMessaging implements Messaging, MessageContainer {

  private static final int MESSAGE_LIMIT = 100;
  private final Resources resources;
  private final List<Message> messages = new ArrayList<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);

  public CollectingMessaging(Resources resources) {
    this.resources = resources;
  }

  @Override
  public MessageToken addMessage(MessageType messageType, String pattern, Object... arguments) {
    return addMessage(messageType, Temporary, pattern, arguments);
  }

  @Override
  public MessageToken addMessage(MessageType messageType, MessageDuration duration, String pattern, Object... arguments) {
    String messageText = resources.getString(pattern, arguments);
    return addMessage(new Message(messageText, messageType, duration));
  }

  @Override
  public MessageToken addMessage(Message message) {
    messages.add(0, message);
    changeControl.announce().changeOccurred();
    if (messages.size() > MESSAGE_LIMIT) {
      messages.remove(messages.size() - 1);
    }
    return new ReplacingToken(message);
  }

  @Override
  public MessageToken obtainInitialToken() {
    return new ReplacingToken();
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public synchronized Message getLatestMessage() {
    if (messages.isEmpty()) {
      return new Message("", MessageType.Normal);
    }
    return messages.get(0);
  }

  @Override
  public List<Message> getPermanentMessages() {
    ArrayList<Message> permanentMessage = new ArrayList<>(messages);
    permanentMessage.removeIf(message -> message.getDuration() == Temporary);
    return permanentMessage;
  }

  private class ReplacingToken implements MessageToken {
    private Message oldMessage;

    public ReplacingToken() {
      this.oldMessage = null;
    }

    public ReplacingToken(Message message) {
      this.oldMessage = message;
    }

    @Override
    public void replaceMessage(Message message) {
      messages.remove(oldMessage);
      addMessage(message);
      this.oldMessage = message;
    }

    @Override
    public void replaceMessage(MessageType type, String pattern, String... arguments) {
      String messageText = resources.getString(pattern, arguments);
      replaceMessage(new Message(messageText, type));
    }
  }
}