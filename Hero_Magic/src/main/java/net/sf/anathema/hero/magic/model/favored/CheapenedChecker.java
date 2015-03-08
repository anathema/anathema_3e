package net.sf.anathema.hero.magic.model.favored;

import net.sf.anathema.magic.data.Magic;

public interface CheapenedChecker {

  boolean supportsMagic(Magic magic);

  boolean isCheapened(Magic magic);
}
