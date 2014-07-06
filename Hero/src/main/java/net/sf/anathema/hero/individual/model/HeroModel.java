package net.sf.anathema.hero.individual.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.library.identifier.Identifier;

public interface HeroModel {

  Identifier getId();

  //TODO (urs): Only Charms, Spells and Equipment make use of the Environment parameter. Refactor to work without. 
  void initialize(HeroEnvironment environment, Hero hero);

  void initializeListening(ChangeAnnouncer announcer);
}
