package net.sf.anathema.charm.data;

import net.sf.anathema.library.resources.Resources;

public class Duration {

  public static final String INSTANT = "Instant";
  public final String text;

  public Duration(String text) {
    this.text = text;
  }

  public String getText(Resources resources) {
    return text;
  }
}