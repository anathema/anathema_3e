package net.sf.anathema.hero.initialization;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.individual.model.change.ChangeAnnouncer;
import net.sf.anathema.library.identifier.Identifier;

public class DummyHeroModel implements HeroModel {
  private Identifier id;

  public DummyHeroModel(Identifier id) {
    this.id = id;
  }

  @Override
  public Identifier getId() {
    return id;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    //nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    //nothing to do
  }
}