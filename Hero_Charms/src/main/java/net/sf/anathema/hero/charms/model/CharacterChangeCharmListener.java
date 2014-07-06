package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.history.ForgotCharmMemento;
import net.sf.anathema.hero.charms.model.history.LearnedCharmMemento;
import net.sf.anathema.hero.charms.model.learn.CharmLearnAdapter;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.HeroChange;

import static net.sf.anathema.hero.individual.change.ChangeFlavor.UNSPECIFIED;

public final class CharacterChangeCharmListener extends CharmLearnAdapter {

  private final ChangeAnnouncer announcer;

  public CharacterChangeCharmListener(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void charmForgotten(Charm charm) {
    announcer.announceChange(new HeroChange(UNSPECIFIED, new ForgotCharmMemento(charm)));
  }

  @Override
  public void charmLearned(Charm charm) {
    announcer.announceChange(new HeroChange(UNSPECIFIED, new LearnedCharmMemento(charm)));
  }

  @Override
  public void recalculateRequested() {
    announcer.announceChangeFlavor(UNSPECIFIED);
  }
}