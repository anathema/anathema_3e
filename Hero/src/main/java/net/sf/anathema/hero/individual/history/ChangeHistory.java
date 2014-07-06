package net.sf.anathema.hero.individual.history;

import net.sf.anathema.hero.individual.change.ChangeFlavor;
import net.sf.anathema.hero.individual.model.Hero;

public class ChangeHistory {

  private Hero hero;

  public ChangeHistory(Hero hero) {
    this.hero = hero;
    this.hero.getChangeAnnouncer().addListener(flavor -> {
      if (!hero.isFullyLoaded()) {
        return;
      }
      addLatestChange(flavor);
    });
  }

  public void addLatestChange(ChangeFlavor flavor) {
    System.err.println("Latest Change: " + flavor);
  }
}
