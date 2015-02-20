package net.sf.anathema.hero.equipment.model;

import java.util.Collection;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.library.event.CollectionListener;

public interface IEquipmentItemCollection {

  IEquipmentItem addItem(String templateId);

  void addEquipmentObjectListener(CollectionListener listener);

  boolean canBeRemoved(IEquipmentItem item);

  String[] getAvailableTemplateIds();

  Collection<IEquipmentItem> getEquipmentItems();

  Collection<IEquipmentItem> getNaturalWeapons();

  void removeItem(IEquipmentItem item);
}