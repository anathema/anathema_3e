package net.sf.anathema.platform.messaging;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.message.Message;
import net.sf.anathema.library.message.MessageDuration;
import net.sf.anathema.library.message.MessageToken;
import net.sf.anathema.library.message.MessageType;
import net.sf.anathema.library.message.Messaging;
import net.sf.anathema.library.resources.Resources;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.library.message.MessageDuration.Permanent;
import static net.sf.anathema.library.message.MessageDuration.Temporary;

public class CollectingMessaging implements Messaging, MessageContainer {

  private static final int MESSAGE_LIMIT = 20;
  private final Resources resources;
  private final List<Message> messages = new ArrayList<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);

  public CollectingMessaging(Resources resources) {
    this.resources = resources;
  }

  @Override
  public MessageToken addPermanentMessage(MessageType messageType, String pattern, Object... arguments) {
    return addMessage(messageType, Permanent, pattern, arguments);
  }

  @Override
  public MessageToken addTemporaryMessage(MessageType messageType, String pattern, Object... arguments) {
    return addMessage(messageType, Temporary, pattern, arguments);
  }

  private MessageToken addMessage(MessageType messageType, MessageDuration duration, String pattern, Object... arguments) {
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
    ReplacingToken token = new ReplacingToken(resources, this);
    token.remember(message);
    return token;
  }

  @Override
  public MessageToken obtainInitialToken() {
    return new ReplacingToken(resources, this);
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

  @Override
  public boolean hasMessages() {
    return !messages.isEmpty();
  }
  
  public void replaceMessage(Message oldMessage, Message message){
    messages.remove(oldMessage);
    addMessage(message);
  }
}