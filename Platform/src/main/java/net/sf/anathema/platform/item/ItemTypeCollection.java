package net.sf.anathema.platform.item;

import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    Stream<ItemTypeConfiguration> configurations = itemTypeConfigurations.stream();
    return configurations.map(ItemTypeConfiguration::getItemType).collect(toList());
  }

  @Override
  public Iterator<IItemType> iterator() {
    return getAllTypes().iterator();
  }
}