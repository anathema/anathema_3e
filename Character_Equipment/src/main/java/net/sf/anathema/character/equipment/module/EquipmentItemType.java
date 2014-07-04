package net.sf.anathema.character.equipment.module;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.item.ItemType;
import net.sf.anathema.platform.item.ItemTypeConfiguration;
import net.sf.anathema.platform.item.RegisteredItemTypeConfiguration;
import net.sf.anathema.platform.item.RepositoryConfiguration;
import net.sf.anathema.platform.repository.SingleFileConfiguration;

@RegisteredItemTypeConfiguration
@Weight(weight=40)
public class EquipmentItemType implements ItemTypeConfiguration {

  private static final String EQUIPMENT_DATABASE_ITEM_TYPE_ID = "EquipmentDatabase";
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new SingleFileConfiguration(".item", "equipment/");
  private static final IItemType ITEM_TYPE = new ItemType(EQUIPMENT_DATABASE_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION, false);

  @Override
  public IItemType getItemType() {
    return ITEM_TYPE;
  }
}