package net.sf.anathema.hero.individual.model;

import net.sf.anathema.hero.individual.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.identifier.Identifier;

public interface Hero extends Iterable<HeroModel> {

  HeroSplat getSplat();

  ChangeAnnouncer getChangeAnnouncer();

  <M extends HeroModel> M getModel(Identifier id);

  boolean isFullyLoaded();
}
