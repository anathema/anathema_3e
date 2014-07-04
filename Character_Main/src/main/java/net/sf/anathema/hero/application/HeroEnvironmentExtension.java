package net.sf.anathema.hero.application;

import net.sf.anathema.hero.environment.HeroEnvironment;

public interface HeroEnvironmentExtension {

  String ID = "CharacterGenericsExtension";

  HeroEnvironment getEnvironment();
}