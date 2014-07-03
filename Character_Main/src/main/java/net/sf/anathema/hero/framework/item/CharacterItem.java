package net.sf.anathema.hero.framework.item;

import net.sf.anathema.hero.application.item.HeroItem;

public class CharacterItem implements Item {

  public static final String DEFAULT_PRINT_NAME = "Unnamed";
  private final ItemData itemData;
  private final ItemRepositoryLocation repositoryLocation = new SimpleRepositoryLocation();

  public CharacterItem(HeroItem heroItem) {
    this.itemData = heroItem;
  }

  @Override
  public ItemData getItemData() {
    return itemData;
  }

  @Override
  public ItemRepositoryLocation getRepositoryLocation() {
    return repositoryLocation;
  }
}