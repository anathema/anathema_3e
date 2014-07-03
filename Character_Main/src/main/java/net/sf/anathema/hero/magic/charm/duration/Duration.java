package net.sf.anathema.hero.magic.charm.duration;

import net.sf.anathema.framework.environment.Resources;

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