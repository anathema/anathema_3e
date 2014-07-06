package net.sf.anathema.hero.traits.model.event;

import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.event.IntegerChangedListener;

public class TraitValueChangedListener implements IntegerChangedListener {
  private final ChangeAnnouncer changeAnnouncer;
  private final Trait trait;

  public TraitValueChangedListener(ChangeAnnouncer changeAnnouncer, Trait trait) {
    this.changeAnnouncer = changeAnnouncer;
    this.trait = trait;
  }

  @Override
  public void valueChanged(int newValue) {
    changeAnnouncer.announceChangeFlavor(new TraitChangeFlavor(trait.getType()));
  }
}
