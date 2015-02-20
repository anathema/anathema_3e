package net.sf.anathema.hero.equipment.display.presenter;

import java.util.Collection;

import net.sf.anathema.equipment.character.IEquipmentItem;

public interface EquipmentItemRenderer {

  String getLabel(IEquipmentItem item);

  Collection<RelativePathWithDisabling> getIcons(IEquipmentItem item);
}