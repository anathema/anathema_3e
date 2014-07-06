package net.sf.anathema.hero.traits.model;

import net.sf.anathema.library.identifier.Identifier;

public interface TraitGroup {
  Iterable<Trait> getGroupTraits();

  Identifier getGroupId();
}
