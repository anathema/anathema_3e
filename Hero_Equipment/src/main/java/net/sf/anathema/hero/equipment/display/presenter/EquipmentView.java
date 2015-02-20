package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.hero.equipment.EquipmentObjectView;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.library.view.VetoableObjectSelectionView;

public interface EquipmentView {

  VetoableObjectSelectionView<String> getEquipmentTemplatePickList();

  Tool addToolButton();

  ObjectSelectionView<IEquipmentItem> addOwnedEquipmentList(EquipmentItemRenderer renderer);

  EquipmentObjectView addItemEditView();
}