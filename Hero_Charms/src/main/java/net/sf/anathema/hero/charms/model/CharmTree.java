package net.sf.anathema.hero.charms.model;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.TreeReference;
import net.sf.anathema.library.identifier.Identifier;

import java.util.Collection;

public interface CharmTree extends Identifier {

  Collection<Charm> getAllCharms();

  TreeReference getReference();

  boolean isCharmFromTree(Charm charm);
}