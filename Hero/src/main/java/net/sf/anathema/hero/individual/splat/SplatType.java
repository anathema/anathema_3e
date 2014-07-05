package net.sf.anathema.hero.individual.splat;

import net.sf.anathema.library.identifier.Identifier;

public interface SplatType {

  HeroType getHeroType();

  Identifier getSubType();
}