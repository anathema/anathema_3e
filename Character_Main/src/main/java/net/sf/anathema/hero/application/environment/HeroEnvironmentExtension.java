package net.sf.anathema.hero.application.environment;

import net.sf.anathema.hero.environment.HeroEnvironment;

public interface HeroEnvironmentExtension {

  String ID = "HeroEnvironmentExtension";

  HeroEnvironment getEnvironment();
}