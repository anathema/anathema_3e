package net.sf.anathema.hero.history.model;

import net.sf.anathema.library.change.ChangeFlavor;

public class ChangeHistory {
  public void addLatestChange(ChangeFlavor flavor) {
    System.err.println("Latest Change: " + flavor);
  }
}
