package net.sf.anathema.hero.application.itemtype;

import net.sf.anathema.platform.item.IItemType;

public class CharacterItemTypeRetrieval {

  public static IItemType retrieveCharacterItemType() {
    return new CharacterItemType().getItemType();
  }
}