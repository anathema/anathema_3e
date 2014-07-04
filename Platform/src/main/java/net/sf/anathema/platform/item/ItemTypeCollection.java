package net.sf.anathema.platform.item;

import com.google.common.collect.Collections2;
import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemTypeCollection implements Iterable<IItemType> {

  private final Collection<ItemTypeConfiguration> itemTypeConfigurations = new ArrayList<>();

  public ItemTypeCollection(ObjectFactory objectFactory) throws InitializationException {
    Collection<ItemTypeConfiguration> configurations = objectFactory.instantiateOrdered(RegisteredItemTypeConfiguration.class);
    itemTypeConfigurations.addAll(configurations);
  }

  public IItemType getById(String id){
    for (IItemType itemType : getAllTypes()) {
      if (itemType.getId().equals(id)) {
        return itemType;
      }
    }
    throw new IllegalArgumentException("No item type registered for id: " + id);
  }

  public Collection<IItemType> getAllTypes() {
    return Collections2.transform(itemTypeConfigurations, ItemTypeConfiguration::getItemType);
  }

  @Override
  public Iterator<IItemType> iterator() {
    return getAllTypes().iterator();
  }
}