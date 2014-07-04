package net.sf.anathema.hero.application.itemtype;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.item.ItemType;
import net.sf.anathema.platform.item.ItemTypeConfiguration;
import net.sf.anathema.platform.item.RegisteredItemTypeConfiguration;
import net.sf.anathema.platform.item.RepositoryConfiguration;
import net.sf.anathema.platform.repository.FolderRepositoryConfiguration;

@RegisteredItemTypeConfiguration
@Weight(weight=10)
public class CharacterItemType implements ItemTypeConfiguration {

  public static final String CHARACTER_ITEM_TYPE_ID = "ExaltedCharacter";
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new FolderRepositoryConfiguration(".ecg", "ExaltedCharacter/", "main");
  private static final IItemType ITEM_TYPE = new ItemType(CHARACTER_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION);

  @Override
  public final IItemType getItemType() {
    return ITEM_TYPE;
  }
}