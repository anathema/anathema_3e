package net.sf.anathema.hero.framework.item;

public class CharacterItem implements Item {

  public static final String DEFAULT_PRINT_NAME = "Unnamed";
  private final ItemData itemData;
  private final ItemRepositoryLocation repositoryLocation = new SimpleRepositoryLocation();

  public CharacterItem(net.sf.anathema.hero.application.item.Character character) {
    this.itemData = character;
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