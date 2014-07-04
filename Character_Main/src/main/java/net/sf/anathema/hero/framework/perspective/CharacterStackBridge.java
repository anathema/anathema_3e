package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;

public interface CharacterStackBridge {

  void addViewForCharacter(CharacterIdentifier identifier, HeroItemData heroItemData);

  void showCharacterView(CharacterIdentifier identifier);
}
