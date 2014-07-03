package net.sf.anathema.hero.charms.model.favored;

import net.sf.anathema.magic.data.Magic;

public interface FavoredChecker {

  boolean supportsMagic(Magic magic);

  boolean isFavored(Magic magic);
}
