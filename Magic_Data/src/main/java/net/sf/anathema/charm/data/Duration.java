package net.sf.anathema.charm.data;

public class Duration {

  public static final String INSTANT = "Instant";
  public final String text;

  public Duration(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}