package net.sf.anathema.library.message;

public interface Messaging {

  MessageToken addPermanentMessage(MessageType messageType, String messagePattern, Object... arguments);

  MessageToken addTemporaryMessage(MessageType messageType, String messagePattern, Object... arguments);

  MessageToken addMessage(Message message);

  MessageToken obtainInitialToken();

}