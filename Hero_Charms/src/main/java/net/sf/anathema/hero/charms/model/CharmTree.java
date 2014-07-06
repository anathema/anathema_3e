package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.library.identifier.Identifier;

import java.util.Collection;

public interface CharmTree extends Identifier {

  Collection<Charm> getAllCharms();

  Collection<Charm> getCoreCharms();

  TreeReference getReference();

  boolean isCharmFromTree(Charm charm);
}