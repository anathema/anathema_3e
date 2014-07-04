package net.sf.anathema.scribe.scroll;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.item.ItemType;
import net.sf.anathema.platform.item.ItemTypeConfiguration;
import net.sf.anathema.platform.item.RegisteredItemTypeConfiguration;
import net.sf.anathema.platform.item.RepositoryConfiguration;
import net.sf.anathema.platform.repository.SingleFileConfiguration;

@RegisteredItemTypeConfiguration
@Weight(weight=20)
public class ScrollItemType implements ItemTypeConfiguration {

  public static final String SCROLL_ITEM_TYPE_ID = "Scroll";
  public static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new SingleFileConfiguration(".scroll", "Scrolls/");
  public static final IItemType ITEM_TYPE = new ItemType(SCROLL_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION);

  @Override
  public IItemType getItemType() {
    return ITEM_TYPE;
  }
}
