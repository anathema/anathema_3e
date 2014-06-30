package net.sf.anathema.hero.template;

import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface TemplateType {

  CharacterType getCharacterType();

  Identifier getSubType();
}