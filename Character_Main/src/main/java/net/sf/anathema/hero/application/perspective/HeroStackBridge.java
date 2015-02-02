package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;

public interface HeroStackBridge {

  void addViewForCharacter(CharacterIdentifier identifier, HeroItemData heroItemData);

  void showCharacterView(CharacterIdentifier identifier);
}
