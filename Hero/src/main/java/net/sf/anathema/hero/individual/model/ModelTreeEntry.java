package net.sf.anathema.hero.individual.model;

import net.sf.anathema.library.identifier.Identifier;

public interface ModelTreeEntry {

  Iterable<Identifier> getRequiredModelIds();

  Identifier getModelId();
}
