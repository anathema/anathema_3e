package net.sf.anathema.hero.framework.type;

import net.sf.anathema.library.identifier.Identifier;

public interface CharacterType extends Identifier {

  boolean isExaltType();

  boolean isEssenceUser();
}