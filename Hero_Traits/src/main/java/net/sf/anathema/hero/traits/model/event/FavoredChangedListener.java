package net.sf.anathema.hero.traits.model.event;

import net.sf.anathema.hero.individual.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.change.ChangeFlavor;
import net.sf.anathema.hero.traits.model.FavorableState;
import net.sf.anathema.hero.traits.model.IFavorableStateChangedListener;

public class FavoredChangedListener implements IFavorableStateChangedListener {
  private final ChangeAnnouncer changeAnnouncer;

  public FavoredChangedListener(ChangeAnnouncer changeAnnouncer) {
    this.changeAnnouncer = changeAnnouncer;
  }

  @Override
  public void favorableStateChanged(FavorableState state) {
    changeAnnouncer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}
