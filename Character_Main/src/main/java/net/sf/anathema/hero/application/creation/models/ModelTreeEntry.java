package net.sf.anathema.hero.application.creation.models;

import net.sf.anathema.library.identifier.Identifier;

public interface ModelTreeEntry {

  Iterable<Identifier> getRequiredModelIds();

  Identifier getModelId();
}
