package net.sf.anathema.platform.messaging;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.message.Message;
import net.sf.anathema.library.message.MessageToken;
import net.sf.anathema.library.message.MessageType;
import net.sf.anathema.library.message.Messaging;
import net.sf.anathema.library.resources.Resources;
import org.jmock.example.announcer.Announcer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CategorizedMessagingImpl implements Messaging, MessageContainer, CategorizedMessaging {
  public static final MessageCategory Default_Message_Category = new MessageCategory("Default");
  private final Map<MessageCategory, CollectingMessaging> categoryMessagingMap = new HashMap<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);
  private final Resources resources;
  private MessageCategory activeCategory = Default_Message_Category;

  public CategorizedMessagingImpl(Resources resources) {
    this.resources = resources;
  }

  public void activateCategory(MessageCategory category) {
    this.activeCategory = category;
    changeControl.announce().changeOccurred();
  }

  @Override
  public MessageToken addPermanentMessage(MessageType messageType, String messagePattern, Object... arguments) {
    return forCategory(activeCategory).addPermanentMessage(messageType, messagePattern, arguments);
  }

  @Override
  public MessageToken addTemporaryMessage(MessageType messageType, String messagePattern, Object... arguments) {
    return forCategory(activeCategory).addTemporaryMessage(messageType, messagePattern, arguments);
  }

  @Override
  public MessageToken addMessage(Message message) {
    return forCategory(activeCategory).addMessage(message);
  }

  @Override
  public MessageToken obtainInitialToken() {
    return new MessageToken() {
      private final Map<MessageCategory, MessageToken> tokenMap = new HashMap<>();

      @Override
      public void replaceMessage(Message message) {
        if (!tokenMap.containsKey(activeCategory)) {
          tokenMap.put(activeCategory, forCategory(activeCategory).obtainInitialToken());
        }
        tokenMap.get(activeCategory).replaceMessage(message);
      }

      @Override
      public void replaceMessage(MessageType type, String pattern, String... arguments) {
        if (!tokenMap.containsKey(activeCategory)) {
          tokenMap.put(activeCategory, forCategory(activeCategory).obtainInitialToken());
        }
        tokenMap.get(activeCategory).replaceMessage(type, pattern, arguments);
      }
    };
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public Message getLatestMessage() {
    return forCategory(activeCategory).getLatestMessage();
  }

  @Override
  public Collection<Message> getPermanentMessages() {
    return forCategory(activeCategory).getPermanentMessages();
  }

  @Override
  public boolean hasMessages() {
    return forCategory(activeCategory).hasMessages();
  }

  private CollectingMessaging forCategory(MessageCategory category) {
    if (!categoryMessagingMap.containsKey(category)) {
      CollectingMessaging messaging = new CollectingMessaging(resources);
      messaging.addChangeListener(() -> changeControl.announce().changeOccurred());
      categoryMessagingMap.put(category, messaging);
    }
    return categoryMessagingMap.get(category);
  }
}
