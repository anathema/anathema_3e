package net.sf.anathema.magic.description.model;

import net.sf.anathema.hero.environment.HeroEnvironment;

public interface MagicDescriptionProviderFactory {

  MagicDescriptionProvider create(HeroEnvironment environment);
}
