package net.sf.anathema.hero.individual.splat;

import net.sf.anathema.library.identifier.Identifier;

public interface HeroType extends Identifier {

  boolean isExaltType();

  boolean isEssenceUser();
}