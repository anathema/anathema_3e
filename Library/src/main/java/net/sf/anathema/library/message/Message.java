package net.sf.anathema.library.message;

public class Message {
  private final MessageType type;
  private final MessageDuration duration;
  private final String text;

  public Message(String text, MessageType type) {
    this(text, type, MessageDuration.Temporary);
  }

  public Message(String text, MessageType type, MessageDuration duration) {
    this.text = text;
    this.type = type;
    this.duration = duration;
  }

  public String getText() {
    return text;
  }

  public MessageType getType() {
    return type;
  }

  public MessageDuration getDuration() {
    return duration;
  }
}