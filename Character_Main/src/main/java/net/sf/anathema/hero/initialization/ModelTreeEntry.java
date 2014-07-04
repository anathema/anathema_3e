package net.sf.anathema.hero.initialization;

import net.sf.anathema.library.identifier.Identifier;

public interface ModelTreeEntry {

  Iterable<Identifier> getRequiredModelIds();

  Identifier getModelId();
}
