package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface CharmTree extends Identifier {

  Charm[] getAllCharms();

  TreeReference getReference();

  boolean isCharmFromTree(Charm charm);
}