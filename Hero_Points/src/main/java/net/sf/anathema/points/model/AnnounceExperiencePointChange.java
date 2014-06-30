package net.sf.anathema.points.model;

import net.sf.anathema.hero.points.advance.experience.ExperiencePointConfigurationListener;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeFlavor;

public class AnnounceExperiencePointChange implements ExperiencePointConfigurationListener {
  private final ChangeAnnouncer announcer;

  public AnnounceExperiencePointChange(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void entriesAddedRemovedOrChanged() {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}