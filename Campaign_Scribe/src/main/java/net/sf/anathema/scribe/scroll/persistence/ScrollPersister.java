package net.sf.anathema.scribe.scroll.persistence;

import net.sf.anathema.platform.repository.printname.RepositoryId;

import java.util.Collection;

public interface ScrollPersister {

  void saveScroll(Scroll scroll);

  Scroll loadScroll(RepositoryId id);

  Scroll newScroll();

  Collection<ScrollReference> listAll();

  boolean hasAny();
}
