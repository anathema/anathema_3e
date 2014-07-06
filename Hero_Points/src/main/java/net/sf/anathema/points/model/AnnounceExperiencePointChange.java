package net.sf.anathema.points.model;

import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.ChangeFlavor;
import net.sf.anathema.points.model.xp.ExperiencePointsListener;

public class AnnounceExperiencePointChange implements ExperiencePointsListener {
  private final ChangeAnnouncer announcer;

  public AnnounceExperiencePointChange(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void entriesAddedRemovedOrChanged() {
    announcer.announceChangeFlavor(ChangeFlavor.UNSPECIFIED);
  }
}