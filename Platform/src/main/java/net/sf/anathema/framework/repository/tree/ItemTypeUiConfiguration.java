package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

public class ItemTypeUiConfiguration extends AbstractUIConfiguration<IItemType> {
  private final Resources resources;
  private final ItemTypePropertiesMap propertiesMap;

  public ItemTypeUiConfiguration(Resources resources, ItemTypePropertiesMap propertiesMap) {
    this.resources = resources;
    this.propertiesMap = propertiesMap;
  }

  @Override
  protected String labelForExistingValue(IItemType value) {
    return resources.getString(propertiesMap.get(value).getLabelKey());
  }

  @Override
  protected RelativePath iconForExistingValue(IItemType value) {
    return propertiesMap.get(value).getIcon();
  }
}
