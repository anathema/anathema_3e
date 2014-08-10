package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.ChangeFlavor;

public class SpecialtiesListener implements ISpecialtyListener {
  private final ChangeAnnouncer changeAnnouncer;

  public SpecialtiesListener(ChangeAnnouncer changeAnnouncer) {
    this.changeAnnouncer = changeAnnouncer;
  }

  @Override
  public void specialtyRemoved(Specialty specialty) {
    changeAnnouncer.announceChangeFlavor(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void specialtyAdded(Specialty specialty) {
    changeAnnouncer.announceChangeFlavor(ChangeFlavor.UNSPECIFIED);
  }
}
