package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.PrintNameFile;

public class ItemTreeUiConfiguration extends AbstractUIConfiguration {

  private final ItemTypePropertiesMap propertiesMap;
  private final ItemTypeUiConfiguration itemtypeUi;

  public ItemTreeUiConfiguration(Environment environment, ItemTypePropertiesMap propertiesMap) {
    this.propertiesMap = propertiesMap;
    this.itemtypeUi = new ItemTypeUiConfiguration(environment, propertiesMap);
  }

  @Override
  protected String labelForExistingValue(Object value) {
    if (value instanceof IItemType) {
      return itemtypeUi.getLabel((IItemType) value);
    }
    if (value instanceof PrintNameFile) {
      PrintNameFile printNameFile = (PrintNameFile) value;
      return getItemTypeUi(printNameFile).getLabel(printNameFile);
    }
    return value.toString();
  }

  @Override
  protected RelativePath iconForExistingValue(Object value) {
    if (value instanceof IItemType) {
      return itemtypeUi.getIconsRelativePath((IItemType) value);
    }
    if (value instanceof PrintNameFile) {
      PrintNameFile printNameFile = (PrintNameFile) value;
      return getItemTypeUi(printNameFile).getIconsRelativePath(printNameFile);
    }
    return null;
  }

  private AgnosticUIConfiguration<PrintNameFile> getItemTypeUi(PrintNameFile value) {
    return propertiesMap.get(value.getItemType()).getItemTypeUI();
  }
}