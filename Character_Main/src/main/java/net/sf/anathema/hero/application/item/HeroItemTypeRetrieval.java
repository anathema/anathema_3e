package net.sf.anathema.hero.application.item;

import net.sf.anathema.platform.item.IItemType;

public class HeroItemTypeRetrieval {

  public static IItemType retrieveCharacterItemType() {
    return new HeroItemType().getItemType();
  }
}