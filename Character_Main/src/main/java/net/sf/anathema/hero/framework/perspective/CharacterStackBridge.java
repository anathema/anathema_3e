package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;

public interface CharacterStackBridge {

  void addViewForCharacter(CharacterIdentifier identifier, net.sf.anathema.hero.application.item.Character character);

  void showCharacterView(CharacterIdentifier identifier);
}
