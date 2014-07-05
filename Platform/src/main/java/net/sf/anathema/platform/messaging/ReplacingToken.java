package net.sf.anathema.platform.messaging;

import net.sf.anathema.library.message.Message;
import net.sf.anathema.library.message.MessageToken;
import net.sf.anathema.library.message.MessageType;
import net.sf.anathema.library.resources.Resources;

public class ReplacingToken implements MessageToken {
  private final Resources resources;
  private final CollectingMessaging messaging;
  private Message oldMessage;

  public ReplacingToken(Resources resources, CollectingMessaging messaging) {
    this.resources = resources;
    this.messaging = messaging;
  }

  @Override
  public void replaceMessage(MessageType type, String pattern, String... arguments) {
    String messageText = resources.getString(pattern, arguments);
    replaceMessage(new Message(messageText, type));
  }

  @Override
  public void replaceMessage(Message message) {
    messaging.replaceMessage(oldMessage, message);
    remember(message);
  }

  public void remember(Message message) {
    this.oldMessage = message;
  }
}