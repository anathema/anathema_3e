package net.sf.anathema.hero.application.itemtype;

import net.sf.anathema.framework.item.IItemType;

public class CharacterItemTypeRetrieval {

  public static IItemType retrieveCharacterItemType() {
    return new CharacterItemType().getItemType();
  }
}