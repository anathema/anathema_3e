package net.sf.anathema.hero.charms.model.favored;

import net.sf.anathema.magic.data.Magic;

public interface CheapenedChecker {

  boolean supportsMagic(Magic magic);

  boolean isCheapened(Magic magic);
}
