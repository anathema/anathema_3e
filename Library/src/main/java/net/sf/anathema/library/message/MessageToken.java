package net.sf.anathema.library.message;

public interface MessageToken {

  void replaceMessage(Message message);

  void replaceMessage(MessageType type, String pattern, String... arguments);
}
