package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.model.MissingMaterialException;
import net.sf.anathema.lib.control.CollectionListener;

import java.util.Collection;

public interface IEquipmentItemCollection {

  IEquipmentItem addItem(String templateId, MagicalMaterial material) throws MissingMaterialException;

  void addEquipmentObjectListener(CollectionListener listener);

  boolean canBeRemoved(IEquipmentItem item);

  String[] getAvailableTemplateIds();

  Collection<IEquipmentItem> getEquipmentItems();

  Collection<IEquipmentItem> getNaturalWeapons();

  void removeItem(IEquipmentItem item);
}