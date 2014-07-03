package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.application.item.HeroItem;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;

public interface CharacterStackBridge {

  void addViewForCharacter(CharacterIdentifier identifier, HeroItem heroItem);

  void showCharacterView(CharacterIdentifier identifier);
}
