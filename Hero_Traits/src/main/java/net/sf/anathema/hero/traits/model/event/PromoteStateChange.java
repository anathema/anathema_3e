package net.sf.anathema.hero.traits.model.event;

import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.ChangeFlavor;
import net.sf.anathema.hero.traits.model.state.TraitStateChangedListener;
import net.sf.anathema.hero.traits.model.state.TraitStateType;

public class PromoteStateChange implements TraitStateChangedListener {
  private final ChangeAnnouncer changeAnnouncer;

  public PromoteStateChange(ChangeAnnouncer changeAnnouncer) {
    this.changeAnnouncer = changeAnnouncer;
  }

  @Override
  public void favorableStateChanged(TraitStateType state) {
    changeAnnouncer.announceChangeFlavor(ChangeFlavor.UNSPECIFIED);
  }
}
