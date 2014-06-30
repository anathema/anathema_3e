package net.sf.anathema.points.model;

import net.sf.anathema.points.model.xp.ExperiencePointsListener;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeFlavor;

public class AnnounceExperiencePointChange implements ExperiencePointsListener {
  private final ChangeAnnouncer announcer;

  public AnnounceExperiencePointChange(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void entriesAddedRemovedOrChanged() {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}