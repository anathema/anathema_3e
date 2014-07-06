package net.sf.anathema.hero.history.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.change.ChangeAnnouncer;
import net.sf.anathema.library.identifier.Identifier;

public class HistoryModelImpl implements HistoryModel, HeroModel {

  private ChangeHistory history = new ChangeHistory();
  private Hero hero;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    announcer.addListener(flavor -> {
      if (!hero.isFullyLoaded()) {
        return;
      }
      history.addLatestChange(flavor);
    });
  }
}
